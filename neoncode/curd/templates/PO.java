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
package com.z2.np.server.po;

import com.z2.np.shared.common.Status;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import java.util.Date;


/**
 * @author YangYongli
 *
 * @hibernate.class  table="{{ tableName }}"
 */
public class {{ java.poClassName }} extends TimedBasePO {
    private Integer id = null;
    {% for field in fields %}
    private {{ field.dataType }} {{ field.name }} = null;
    {% endfor %}

    /**
    * @hibernate.id generator-class="native"
    */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    {% for field in fields %}
    /**
    * @hibernate.property
    * @return {{ field.name }} 
    */
    public {{ field.dataType }} get{{ field.name|upper0 }}() {
        return {{ field.name }};
    }

    public void set{{ field.name|upper0 }}({{ field.dataType }} {{ field.name }}) {
        this.{{ field.name }} = {{ field.name }};
    }
    {% endfor %}

    public Serializable getPK() {
        return id;
    }

    public void setPK(Serializable pk) {
        this.id = (Integer)pk;
    }

    public void setStatus(Status status) {
  
    }
}
