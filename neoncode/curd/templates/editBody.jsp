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

<script language="javascript">
function doSave(){
    return true;
}

function doCancel(){
    document.location.href="/np{{ action.view }}.do?id=<bean:write name="{{ java.formBeanClassName }}" property="{{ java.voClassName|lower0 }}.id" ignore="true"/>&";
}

</script>

<html:form method="POST" action="{{ action.save }}" onsubmit="return doSave();">
<html:errors />

<!-- Section Header -->
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
    <TR>
        <TD vAlign=bottom width=80%>
        <SPAN class="contentHeader">{{ titleName }} : <bean:write name="{{ java.formBeanClassName }}" property="{{ java.voClassName|lower0 }}.repr" ignore="true"/></SPAN>
        </TD>
        <TD align=right>

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
            <TD class=divideLineBlack colspan=2><html:img page="/images/s.gif" width="1" height="1" /></TD>
            <TD></TD>
            <TD class=divideLineBlack colspan=2><html:img page="/images/s.gif"/></TD>
        </TR>

    {% for field in fields %}
        {% if loop.index0 is divisibleby(2) %}
        <TR>
        {% endif %}
            <TD class="editLabel {% if field.required %} requiredB {% endif %}" vAlign=top noWrap>{{ field.title }}:</TD>
            <TD class="editField" vAlign=top>
            {% if field.widgetType == "TextArea" %}
            <html:textarea property="{{ java.voClassName|lower0 }}.{{ field.name }}" rows="3" cols="80" />
            {% elif field.widgetType == "DropDown" %}
            <html:select property="{{ java.voClassName|lower0 }}.{{ field.name }}" styleClass="selectMedium" >
                <neon:options mappingName="{{ field.mapping }}" labelDisplayed=""/>
            </html:select>
            {% else %}
            <html:text property="{{ java.voClassName|lower0 }}.{{ field.name }}" styleClass="inputLong" />
            {% endif %}
            </TD>
        {% if loop.index0 is divisibleby(2) %}
            <TD></TD>
            {% if loop.last %}
            <TD class=viewLabel vAlign=top noWrap>&nbsp;</TD>
            <TD class=viewField vAlign=top>&nbsp;</TD>
        </TR>
            {% endif %}
        {% else %}
        </TR>
        {% endif %}
    {% endfor %}
    
        <tr>
            <td colspan=5 align=center> 
            <html:submit property="save" value=" Save " styleClass="button primary" onclick="if (window.event) window.event.cancelBubble = true; else event.cancelBubble = true; return true;"/>
              &nbsp;&nbsp;&nbsp; 
            <html:button property="cancel" value=" Cancel " styleClass="button" onclick="doCancel(); return false" />
            </td>
        </tr>
        <tr>
            <td colspan=5><img src="/np/images/s.gif" height=3></td>
        </tr>
        <tr>
            <td colspan=5>
            <!--Note here-->
            </td>
        </tr>
        </TBODY>
        </TABLE>
    </TD>
    </TR>
</TABLE>
</html:form>

    