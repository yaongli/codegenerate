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
package {{ action.formBeanClassPackage }};

import java.util.Date;
import java.util.List;

import com.z2.np.client.formbean.BaseFormBean;

/**
 * @author YangYongli
 * 
 */
public class {{ action.formBeanClassName }} extends BaseFormBean {

    {% for field in search %}
        {% if field.dataType == "DateTime" %}
    private Date {{ field.name }} = null;
        {% else %}
    private String {{ field.name }} = null;
        {% endif %}
    {% endfor %}
    
    {% for field in search %}
        {% if field.dataType == "DateTime" %}
    public Date get{{ field.name|capitalize }}() {
        return {{ field.name }};
    }

    public void set{{ field.name|capitalize }}(Date {{ field.name }}) {
        this.{{ field.name }} = {{ field.name }};
    }
        {% else %}
    public String get{{ field.name|capitalize }}() {
        return {{ field.name }};
    }

    public void set{{ field.name|capitalize }}(String {{ field.name }}) {
        this.{{ field.name }} = {{ field.name }};
    }
        {% endif %}
    {% endfor %}
}
