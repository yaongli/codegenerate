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
package {{ java.formBeanClassPackage }};

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;

import com.z2.np.client.formbean.BaseFormBean;
import com.z2.np.shared.valueobject.{{ java.voClassName }};

/**
 * @author YangYongli
 * 
 */
public class {{ java.formBeanClassName }} extends BaseFormBean {

    private {{ java.voClassName }} {{ java.voClassName|lower0 }} = new {{ java.voClassName }}();
    
    public {{ java.voClassName }} get{{ java.voClassName }}() {
        return {{ java.voClassName|lower0 }};
    }

    public void set{{ java.voClassName }}({{ java.voClassName }} {{ java.voClassName|lower0 }}) {
        this.{{ java.voClassName|lower0 }} = {{ java.voClassName|lower0 }};
    }

    /**
    * @return ActionMessages
    */
    public ActionMessages validateFormBean() {
        ActionMessages messages = new ActionMessages();
        if(null != {{ java.voClassName|lower0 }}){
            {% for field in fields %}
            {% if field.required %}
            if(StringUtils.isBlank({{ java.voClassName|lower0 }}.get{{ field.name|upper0 }}())){
                messages.add("{{ field.name|upper0 }}Required", new ActionMessage("errors.required", "{{ field.title }}"));
            }
            {% endif %}
            {% endfor %}
        }
        
        return messages;
    }
}
