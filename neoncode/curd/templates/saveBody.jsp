<%@page import="com.z2.np.shared.util.html.HtmlUtils"%>
<%@page language="java" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/NeonTags.tld" prefix="neon"%>

<%@page import="com.z2.np.client.GlobalSetting,
                java.text.DateFormat,
                com.z2.np.shared.valueobject.*,
                com.z2.np.shared.util.StringEscapeUtils,
                com.z2.np.shared.common.*,
                java.util.*,
                com.z2.np.client.util.NavigationListType,
                com.z2.np.client.action.ActionUtils,
                com.z2.np.shared.report.NeonDynaBean,
                java.text.DecimalFormat,
                com.z2.np.shared.util.NumberUtils" %>
<jsp:useBean id="{{ java.formBeanClassName|lower0 }}" class="{{ java.formBeanClass }}" scope="session" />

<html:errors />

<script language="javascript">
function doDelete(){
    if(!confirm("Are you sure?")){
        return false;
    }
    document.location.href="/np{{ action.delete }}.do?id=<bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.id" />&";
}

</script>

<!-- Section Header -->
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
    <tr>
      <td vAlign=bottom width=80%>
        <SPAN class="contentHeader">{{ titleName }} : <bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.name" ignore="true"/></SPAN>
      </TD>
      <TD align=right>
        <jsp:include page="/admin/common/navigationInclude.jsp">
           <jsp:param name="nlType" value="<%=NavigationListType.PRODUCT_DETAIL.getValue()%>"/>
        </jsp:include>
      </TD>
      </tr>
    <tr>
      <td class="sectionLine" colSpan=2><html:img page="/images/s.gif" height="2" width="1"/></td></tr>
    <TR>
    <TD vAlign=top align=left colspan=2>
      <!--main body -->
      <TABLE cellSpacing=1 cellPadding=1 width="100%" border=0>
      <TBODY>
      <TR>
        <TD width=100><html:img page="/images/s.gif" width="1" height="1" /></TD>
        <TD width=40%><html:img page="/images/s.gif" width="1" height="1" /></TD>
        <TD width=5></TD>
        <TD width=100><html:img page="/images/s.gif"/></TD>
        <TD width=40%><html:img page="/images/s.gif"/></TD></TR>
         <TR>
           <TD class="textBold" noWrap align=left colspan=5><!--Section Title-->
           <neon:auth code="<%=com.z2.np.shared.common.security.AccessPointEnumeration.EDIT_{{ tableName|upper }}.getValueText()%>">
           &nbsp;<input type="button" name="edit" value=" Edit " onclick="window.location='<%=request.getContextPath()%>{{ action.edit }}.do?id=<bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.id"/>';" class="button"/>
           </neon:auth>
           <neon:auth code="<%=com.z2.np.shared.common.security.AccessPointEnumeration.DELETE_{{ tableName|upper }}.getValueText()%>">
           &nbsp;<input type="button" name="delete" value=" Delete " onclick="doDelete()" class="button"/>
           </neon:auth>
           &nbsp;
           </TD>
         </TR>
      <TR>
        <TD class=divideLineBlack colspan=2><html:img page="/images/s.gif" width="1" height="1" /></TD>
        <TD></TD>
        <TD class=divideLineBlack colspan=2><html:img page="/images/s.gif"/></TD>
      </TR>

     {% for field in fields %}
     {% if loop.index is divisibleby(2) %}
     <TR>
        <TD class=viewLabel vAlign=top noWrap>Name: </TD>
        <TD class=viewField vAlign=top><bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.{{ field.name }}" ignore="true"/></TD>
        <TD></TD>
     {% else %}
        <TD class=viewLabel vAlign=top noWrap>Code: </TD>
        <TD class=viewField vAlign=top><bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.{{ field.name }}" ignore="true"/></TD>
     </TR>
     {% endif %}
     {% if loop.index is not divisibleby(2) and loop.last %}
        <TD class=viewLabel vAlign=top noWrap>&nbsp;</TD>
        <TD class=viewField vAlign=top>&nbsp;</TD>
     </TR>
     {% endif %}
     {% endfor %}

     <TR>
        <TD class=divideLineBlack colSpan=5><html:img page="/images/s.gif" width="1" height="1" /></TD>
     </TR>
     <TR>
        <TD class=viewLabel vAlign=top noWrap>Created:</TD>
        <TD class=viewField vAlign=top>
            <bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.createTime" format="MM/dd/yyyy HH:mm:ss"
            ignore="true" />
            By 
            <bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.createBy"
            ignore="true" />
        </TD>
        <TD></TD>
        <TD class=viewLabel vAlign=top noWrap>Last Updated:</TD>
        <TD class=viewField vAlign=top><bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.lastModifyTime" format="MM/dd/yyyy HH:mm:ss"
            ignore="true" />
            By 
            <bean:write name="{{ java.formBeanClassName|lower0 }}" property="{{ java.voClassName|lower0 }}.lastModifyBy"
            ignore="true" />
        </TD>
    </TR>
    </TBODY>
    </TABLE>
</TD>
</TR>
</TABLE>

    