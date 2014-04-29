/*
 * ================================================================
 *
 * Copyright 2013, Z2 Systems, Inc. All rights reserved.
 *
 * The Programs (which include both the software and documentation)
 * contain proprietary information of Z2 Systems, Inc.; they are 
 * provided under a license agreement containing restrictions on 
 * use and disclosure and are also protected by copyright, patent 
 * and other intellectual and industrial property laws. Reverse 
 * engineering, disassembly or decompilation of the Programs, 
 * except to the extent required to obtain interoperability with 
 * other independently created software or as specified by law, 
 * is prohibited.
 *
 * The information contained in this document is subject to change 
 * without notice. If you find any problems in the documentation, 
 * please report them to us in writing. Z2 Systems, Inc. does not 
 * warrant that this document is error free. Except as may be 
 * expressly permitted in your license agreement for these Programs, 
 * no part of these Programs may be reproduced or transmitted in any 
 * form or by any means, electronic or mechanical, for any purpose, 
 * without the express written permission of Z2 Systems, Inc.
 * 
 * ================================================================
 */
package {{ java.actionClassPackage }};

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.z2.np.client.NeonClientLogger;
import com.z2.np.client.NeonExceptionHandler;
import com.z2.np.client.action.BaseAction;
import com.z2.np.client.util.NavigationController;
import com.z2.np.client.util.NavigationList;
import net.mlw.vlh.DefaultListBackedValueList;
import net.mlw.vlh.ValueList;

import com.z2.np.shared.facade.*;
import com.z2.np.shared.util.*;
import com.z2.np.shared.valueobject.*;
import com.z2.np.shared.valueobject.data.*;
import com.z2.np.client.valuelist.ValueListAction;
import com.z2.np.client.valuelist.ValueListInfo;

import {{ java.formBeanClass }};
import {{ java.voClass }};

/**
 * @author YangYongli
 * 
 */
public class {{ java.actionClassName }} extends ValueListAction {

    public ActionForward execute(ActionMapping mapping, 
        ActionForm actionForm, 
        HttpServletRequest request, 
        HttpServletResponse response) {
        try {
            Logger logger = NeonClientLogger.getLogger( request );
            String parameter = mapping.getParameter();
            if ( logger.isDebugEnabled() ){
                logger.debug(" *** Entering {{ java.actionClassName }} " + parameter + " *** ");
            }
            UserContext userContext = getUserContext(request);
            
            {{ java.formBeanClassName }} form = ({{ java.formBeanClassName }})actionForm;
            if("list".equals(parameter)){
                return list(mapping, actionForm, request, response);   
            }else if ("create".equals(parameter)){
                return create(mapping, actionForm, request, response);   
            }else if ("view".equals(parameter)){
                return view(mapping, actionForm, request, response);   
            }else if ("edit".equals(parameter)){
                return edit(mapping, actionForm, request, response);   
            }else if ("save".equals(parameter)){
                return save(mapping, actionForm, request, response);   
            }else if ("delete".equals(parameter)){
                return delete(mapping, actionForm, request, response);   
            }
            return null;
        } catch ( Exception e ) {
            NeonExceptionHandler.handleException(request, e);
            return mapping.findForward("runtime-error");
        } 
    }

    public ActionForward list(ActionMapping mapping, 
        ActionForm actionForm, 
        HttpServletRequest request, 
        HttpServletResponse response) {
        try {
            Logger logger = NeonClientLogger.getLogger(request);
            UserContext userContext = getUserContext(request);
            
            {{ java.formBeanClassName }} form = ({{ java.formBeanClassName }})actionForm;

            ValueListInfo listInfo = getValueListInfo(request, Page.DEFAULT_RECORDS_PER_PAGE, "lastModifyTime", -1);
            Page page = getPage(listInfo);
            page.setQueryString("select * from {{ tableName }} ");
            Page result = get{{ Facade }}().retrieve{{ baseName }}List(page, userContext);      
            ValueList valueList = getValueList(result, listInfo);
            request.setAttribute("valueList", valueList);

            String[] cacheColumns = {"id"};
            NavigationList nl = NavigationController.getInstance(request.getSession()).cacheData(result, result.getResults(), cacheColumns, request, true);
            nl.getPage().setGetCount(true);

            return mapping.findForward("success");
        } catch ( Exception e ) {
            NeonExceptionHandler.handleException(request, e);
            return mapping.findForward("runtime-error");
        } 
    }

    public ActionForward create(ActionMapping mapping, 
        ActionForm actionForm, 
        HttpServletRequest request, 
        HttpServletResponse response) {
        try {
            Logger logger = NeonClientLogger.getLogger(request);
            UserContext userContext = getUserContext(request);
            
            {{ java.formBeanClassName }} form = ({{ java.formBeanClassName }})actionForm;
            form.set{{ java.voClassName }}(new {{ java.voClassName }}());
            return mapping.findForward("success");
        } catch ( Exception e ) {
            NeonExceptionHandler.handleException(request, e);
            return mapping.findForward("runtime-error");
        } 
    }

    public ActionForward view(ActionMapping mapping, 
        ActionForm actionForm, 
        HttpServletRequest request, 
        HttpServletResponse response) {
        try {
            Logger logger = NeonClientLogger.getLogger(request);
            UserContext userContext = getUserContext(request);
            Integer id = Integer.parseInt(NeonUtils.tidyIntParam(request.getParameter("id")));
            {{ java.voClassName }} {{ baseName|lower0 }} = get{{ Facade }}().retrieve{{ baseName }}(id, userContext);

            if ( {{ baseName|lower0 }} != null ) {
                {{ java.formBeanClassName }} form = ({{ java.formBeanClassName }})actionForm;
                form.set{{ java.voClassName }}({{ baseName|lower0 }});

                return mapping.findForward("success");
            } else {
                return mapping.findForward("failure");
            }
        } catch ( Exception e ) {
            NeonExceptionHandler.handleException(request, e);
            return mapping.findForward("runtime-error");
        } 
    }

    public ActionForward edit(ActionMapping mapping, 
        ActionForm actionForm, 
        HttpServletRequest request, 
        HttpServletResponse response) {
        try {
            Logger logger = NeonClientLogger.getLogger(request);
            UserContext userContext = getUserContext(request);
            Integer id = Integer.parseInt(NeonUtils.tidyIntParam(request.getParameter("id")));
            {{ java.voClassName }} {{ baseName|lower0 }} = get{{ Facade }}().retrieve{{ baseName }}(id, userContext);

            if ( {{ baseName|lower0 }} != null ) {
                {{ java.formBeanClassName }} form = ({{ java.formBeanClassName }})actionForm;
                form.set{{ java.voClassName }}({{ baseName|lower0 }});

                return mapping.findForward("success");
            } else {
                return mapping.findForward("failure");
            }
        } catch ( Exception e ) {
            NeonExceptionHandler.handleException(request, e);
            return mapping.findForward("runtime-error");
        } 
    }

    public ActionForward save(ActionMapping mapping, 
        ActionForm actionForm, 
        HttpServletRequest request, 
        HttpServletResponse response) {
        try {
            Logger logger = NeonClientLogger.getLogger(request);
            UserContext userContext = getUserContext(request);
            
            {{ java.formBeanClassName }} form = ({{ java.formBeanClassName }})actionForm;
            {{ java.voClassName }} {{ baseName|lower0 }} = form.get{{ java.voClassName }}();
            ActionMessages messages = form.validateFormBean();
            if ( null != messages &&  messages.size() > 0 ) {
                saveErrors(request, messages);
                if(ipBlocking.isNew()){
                    return mapping.findForward("create");
                }else{
                    return mapping.findForward("edit");
                }
            }

            if({{ baseName|lower0 }}.isNew()){
                logger.debug("save new {{ java.voClassName }} ");
                {{ baseName|lower0 }} = get{{ Facade }}().create{{ baseName }}({{ baseName|lower0 }}, userContext);
            } else {
                logger.debug("update {{ java.voClassName }} ");
                {{ baseName|lower0 }} = get{{ Facade }}().update{{ baseName }}({{ baseName|lower0 }}, userContext);
            }

            form.set{{ java.voClassName }}({{ baseName|lower0 }});
            return mapping.findForward("success");
        } catch ( Exception e ) {
            NeonExceptionHandler.handleException(request, e);
            return mapping.findForward("runtime-error");
        } 
    }

    public ActionForward delete(ActionMapping mapping, 
        ActionForm actionForm, 
        HttpServletRequest request, 
        HttpServletResponse response) {
        try {
            Logger logger = NeonClientLogger.getLogger(request);
            UserContext userContext = getUserContext(request);
            String id = NeonUtils.tidyIntParam(request.getParameter("id"));
            DeleteSpec ds = new DeleteSpec("{{ tableName }}", id);
            ds.setReturnPath(new ActionForward(mapping.findForward("view").getPath() + "?id=" + id, true));
            ds.setSuccessReturnPath(mapping.findForward("list"));
            request.setAttribute("deleteSpec", ds);
            return mapping.findForward("commonDelete");
        } catch ( Exception e ) {
            NeonExceptionHandler.handleException(request, e);
            return mapping.findForward("runtime-error");
        } 
    }
}
