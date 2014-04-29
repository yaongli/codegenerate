/*
 * ================================================================
 *
 * Copyright 2014, Z2 Systems, Inc. All rights reserved.
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
package {{ action.actionClassPackage }};

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.mlw.vlh.ValueListInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.z2.np.client.NeonClientLogger;
import com.z2.np.client.action.ActionUtils;
import com.z2.np.client.valuelist.AbstractValueListAction;
import com.z2.np.server.manager.security.AccessType;
import com.z2.np.shared.common.ModuleType;
import com.z2.np.shared.exception.AccessDeniedException;
import com.z2.np.shared.report.NeonDynaBean;
import com.z2.np.shared.util.DateUtils;
import com.z2.np.shared.util.StringEscapeUtils;
import com.z2.np.shared.valueobject.UserContext;

import {{ action.formBeanClass }};

/**
 * @author YangYongli
 *
 */
public class {{ action.actionClassName }} extends AbstractValueListAction {
    
    protected void initAction(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request) throws Exception {
        Logger logger = NeonClientLogger.getLogger(request);
        logger.info("Begin to init {{ action.actionClassName }}");
        UserContext userContext = ActionUtils.getUserContext(request);
        {{ action.formBeanClassName }} form = {{ action.formBeanClassName }} actionForm;
        {% for field in search %}
        form.set{{ field.name|capitalize }}(null);
        {% endfor %}
        //Init here

        logger.info("End to init {{ action.actionClassName }}");
    }

    @Override
    protected void securityCheck(UserContext userContext) throws Exception {
        //if(!"z2".equals(userContext.getOrgId())){
        //     throw new AccessDeniedException(100, false);
        //}

        getSystemSettingFacade().checkUserInRole(ModuleType.SYSTEM_SETTING, AccessType.READ, userContext);
    }

    @Override
    protected void handleRecord(NeonDynaBean bean, HttpServletRequest request) throws Exception {
        if(StringUtils.isNotBlank(bean.getString("accountId"))){
            Integer accountId = Integer.parseInt(bean.getString("accountId"));
            String accountName = bean.getString("accountName");
            String link = "/np/admin/account/accountDetails.do?id=" + accountId + "&";
            accountName = "<a href='" + link + "' >" + accountName + "</a>";
            bean.set("accountName", accountName);
        }

        String actionTemplate = "<NOBR><a href='/np{{ curd.update }}.do?id=${id}' class='actionLink' >Edit</a>&nbsp;" +
                "|&nbsp;<a href='/np{{ curd.delete }}.do?id=${id}' class='actionLink' onclick=\"return window.confirm('Are you sure?');\">Delete</a></NOBR>";
        
        String id = bean.getString("id");
        String action = StringUtils.replace(actionTemplate, "${id}", id);
        bean.set("action", action);
    }

    @Override
    protected String provideQuerySql(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request) throws Exception {
        {{ action.formBeanClassName }} form = {{ action.formBeanClass }} formBeanClassName;
        String condition = " where 1=1 ";
        
        {% for field in search %}
        if(StringUtils.isNotBlank(form.get{{ field.name|capitalize }}())){
            String {{ field.name }} = StringEscapeUtils.escapeSql(form.get{{ field.name|capitalize }}());
            condition += " and ({{ field.name }} = '" + {{ field.name }} + "') ";
        }

        {% endfor %}
        
        String query = {{ query_sql_escape }} + condition;
        return query;
    }

    @Override
    protected String provideDefaultSortColumn() {
        return "  ";
    }

    @Override
    protected Integer provideDefaultSortDirection() {
        return ValueListInfo.ASCENDING;
    }
}
