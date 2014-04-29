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
<jsp:useBean id="{{ java.formBeanClassName }}" class="{{ java.formBeanClass }}" scope="session" />

<html:errors />

<script language="javascript">
function doDelete(){
    if(!confirm("Are you sure?")){
        return false;
    }
    document.location.href="/np{{ action.delete }}.do?id=<bean:write name="{{ java.formBeanClassName }}" property="{{ java.voClassName|lower0 }}.id" />&";
}

</script>

<!-- Section Header -->
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
    <TR>
        <TD vAlign=bottom width=80%>
        <SPAN class="contentHeader">{{ titleName }} : <bean:write name="{{ java.formBeanClassName }}" property="{{ java.voClassName|lower0 }}.repr" ignore="true"/></SPAN>
        </TD>
        <TD align=right>
        <jsp:include page="/admin/common/navigationInclude.jsp">
           <jsp:param name="nlType" value="<%=NavigationListType.{{ tableName|upper }}_DETAIL.getValue()%>"/>
           <jsp:param name="returnListUrl" value="/np{{ action.list }}.do"/>
        </jsp:include>
        </TD>
    </TR>
    <TR>
        <TD class="sectionLine" colSpan=2><html:img page="/images/s.gif" height="2" width="1"/></TD></TR>
    <TR>
    <TD vAlign=top align=left colspan=2>
        <!--main body -->
        <TABLE cellSpacing=1 cellPadding=1 width="100%" border=0>
        <TBODY>
        <TR>
            <TD width="15%"><html:img page="/images/s.gif" width="1" height="1" /></TD>
            <TD width="34%"><html:img page="/images/s.gif" width="1" height="1" /></TD>
            <TD width="1%"></TD>
            <TD width="15%"><html:img page="/images/s.gif"/></TD>
            <TD width="35%"><html:img page="/images/s.gif"/></TD>
        </TR>
        <TR>
            <TD class="textBold" noWrap align=left colspan=5><!--Section Title-->
                {% if needNeonAuth %}
                <neon:auth code="<%=com.z2.np.shared.common.security.AccessPointEnumeration.EDIT_{{ tableName|upper }}.getValueText()%>">
                {% endif %}
                &nbsp;<input type="button" name="edit" value=" Edit " onclick="window.location='<%=request.getContextPath()%>{{ action.edit }}.do?id=<bean:write name="{{ java.formBeanClassName }}" property="{{ java.voClassName|lower0 }}.id"/>';" class="button"/>
                {% if needNeonAuth %}
                </neon:auth>
                <neon:auth code="<%=com.z2.np.shared.common.security.AccessPointEnumeration.DELETE_{{ tableName|upper }}.getValueText()%>">
                {% endif %}
                &nbsp;<input type="button" name="delete" value=" Delete " onclick="doDelete()" class="button"/>
                {% if needNeonAuth %}
                </neon:auth>
                {% endif %}
                &nbsp;
            </TD>
        </TR>
        <TR>
            <TD class=divideLineBlack colspan=2><html:img page="/images/s.gif" width="1" height="1" /></TD>
            <TD></TD>
            <TD class=divideLineBlack colspan=2><html:img page="/images/s.gif"/></TD>
        </TR>

    {% for field in fields %}
        {% if loop.index0 is divisibleby(2) %}
        <TR>
        {% endif %}
            <TD class="viewLabel" vAlign="top" noWrap>{{ field.title }}:</TD>
            <TD class="viewField" vAlign="top"><bean:write name="{{ java.formBeanClassName }}" property="{{ java.voClassName|lower0 }}.{{ field.name }}" ignore="true"/></TD>
        {% if loop.index0 is divisibleby(2) %}
            <TD></TD>
            {% if loop.last %}
            <TD class="viewLabel" vAlign="top" noWrap>&nbsp;</TD>
            <TD class="viewLabel" vAlign="top">&nbsp;</TD>
        </TR>
            {% endif %}
        {% else %}
        </TR>
        {% endif %}
    {% endfor %}

        <TR>
            <TD class=divideLineBlack colSpan=5><html:img page="/images/s.gif" width="1" height="1" /></TD>
        </TR>
        <TR>
            <TD class=viewLabel vAlign=top noWrap>Created:</TD>
            <TD class=viewField vAlign=top>
                <bean:write name="{{ java.formBeanClassName }}" property="{{ java.voClassName|lower0 }}.createdDisplay"  ignore="true" />
            </TD>
            <TD></TD>
            <TD class=viewLabel vAlign=top noWrap>Last Updated:</TD>
            <TD class=viewField vAlign=top>
                <bean:write name="{{ java.formBeanClassName }}" property="{{ java.voClassName|lower0 }}.lastModifiedDisplay" ignore="true" />
            </TD>
        </TR>
        </TBODY>
        </TABLE>
    </TD>
    </TR>
</TABLE>

    