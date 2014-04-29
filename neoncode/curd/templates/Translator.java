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
package com.z2.np.server.translator;

import com.z2.np.shared.valueobject.BaseVO;
import com.z2.np.shared.valueobject.{{ java.voClassName }};
import com.z2.np.server.po.{{ java.poClassName }};
import com.z2.np.server.po.BasePO;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * @author YangYongli
 *
 */
public class {{ java.transClassName }} extends BaseTranslator {

    public boolean translateVoToPo(BaseVO vo1, BasePO po1) {
        if(super.translateVoToPo(vo1,po1)==false){
            return false;
        }
        {{ java.voClassName }} vo = ({{ java.voClassName }}) vo1;
        {{ java.poClassName }} po = ({{ java.poClassName }}) po1;
        if(vo.getId()!=null){
            po.setId(new Integer(vo.getId()));
        }

        {% for field in fields %}
        {% if field.dataType == "Date" %} 
        po.set{{ field.name|upper0 }}(translateDate(vo.get{{ field.name|upper0 }}()));
        {% else %}
        po.set{{ field.name|upper0 }}(vo.get{{ field.name|upper0 }}());
        {% endif %}
        {% endfor %}
        return true;
    }

    public boolean translatePoToVo(BasePO po1, BaseVO vo1) {
        if(super.translatePoToVo(po1,vo1)==false){
            return false;
        }
        {{ java.voClassName }} vo = ({{ java.voClassName }}) vo1;
        {{ java.poClassName }} po = ({{ java.poClassName }}) po1;
        if(null != po.getId()){
            vo.setId(String.valueOf(po.getId()));
        }

        {% for field in fields %}
        {% if field.dataType == "Date" %} 
        vo.set{{ field.name|upper0 }}(translateDate(po.get{{ field.name|upper0 }}()));
        {% else %}
        vo.set{{ field.name|upper0 }}(po.get{{ field.name|upper0 }}());
        {% endif %}
        {% endfor %}
        return true;
    }

    public {{ java.poClassName }} translate({{ java.voClassName }} vo){
        if(vo==null){
            return null;
        }

        {{ java.poClassName }} po = new {{ java.poClassName }}();
        translateVoToPo(vo, po);
        return po;
    }

    public {{ java.voClassName }} translate({{ java.poClassName }} po){
        if(po==null){
            return null;
        }
        {{ java.voClassName }} vo = new {{ java.voClassName }}();
        translatePoToVo(po, vo);
        return vo;
   }
}
