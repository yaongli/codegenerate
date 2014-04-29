<%@page import="com.z2.np.shared.report.NeonDynaBean,
                 com.z2.np.shared.util.NameUtils,
                 java.util.List,
                 net.mlw.vlh.ValueList,
                 java.util.HashSet,
                 java.util.Iterator,
                 org.apache.commons.beanutils.LazyDynaClass,
                 com.z2.np.client.util.NavigationListType"%>
<%@ taglib prefix="vlh" uri="/WEB-INF/valuelist.tld" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/NeonTags.tld" prefix="neon" %>

{% if needNeonAuth %}
<neon:auth code="<%= AccessPointEnumeration.VIEW_{{ tableName|upper }}.getValueText()%>">
{% endif %}

<script language="javascript">
function doDelete(itemId){
    if(!confirm("Are you sure?")){
        return false;
    }
    document.location.href="/np{{ action.delete }}.do?id=" + itemId + "&";
}

</script>

<!-- Section Header -->
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
    <tr>
      <td vAlign=bottom width=80%>
        <SPAN class="contentHeader">{{ titleName }} List</SPAN> &nbsp;&nbsp;&nbsp;&nbsp;
        {% if needNeonAuth %}
        <neon:auth code="<%=com.z2.np.shared.common.security.AccessPointEnumeration.NEW_{{ tableName|upper }}.getValueText()%>">
           &nbsp;<input type="button" name="create" value=" New {{ titleName }}" onclick="window.location='<%=request.getContextPath()%>{{ action.create }}.do';" class="button primary"/>
           </neon:auth>
        {% else %}
            &nbsp;<input type="button" name="create" value=" New {{ titleName }}" onclick="window.location='<%=request.getContextPath()%>{{ action.create }}.do';" class="button primary"/>
        {% endif %}
      </TD>
      <TD align=right>
        
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
        <TD width=40%><html:img page="/images/s.gif"/></TD>
      </TR>
 
     
    <TR>
      <TD colspan="5">
      <vlh:root configName="reportLook" value="valueList" url="/np{{ action.list }}.do?" includeParameters="*">
        <table class="reportLook">
            <vlh:row bean="report" nlCache="id" attributes2="style=\"cursor: pointer;\" onclick=\"document.location.href='/np{{ action.view }}.do?id=${id}&'\"; ">
                <vlh:column title="Action"   default="<NOBR><A href='/np{{ action.edit }}.do?id=${id}&'>Edit</A>&nbsp;|&nbsp;<a href='/np{{ action.delete }}.do?id=${id}&' class='actionLink' onclick='return confirm(\"Are you sure you want to delete it?\");'>Delete</a></NOBR>" attributes="width='100'" />
      {% for field in fields %}
                  <vlh:column title="{{ field.title }}" property="{{ field.name }}" sortable="asc" attributes="width='250'" {% if field.format %} format="{{ field.format }}" {% endif %} {% if field.mapping %} mapping="{{ field.mapping }}" {% endif %} />
      {% endfor %}
                  <vlh:column title="Create Time" property="createTime" sortable="desc"  attributes="width='110'" format="MM/dd/yyyy" />
                  <vlh:column title="Update Time" property="lastModifyTime" sortable="desc"  attributes="width='110'" format="MM/dd/yyyy" />
              </vlh:row>
          </table>
          <vlh:paging pages="5" showSummary="true"> <c:out value="${page}" />&nbsp;</vlh:paging>
      </vlh:root>
    </TD>
    </TR>
    </TBODY>
    </TABLE>
</TD>
</TR>
</TABLE>
{% if needNeonAuth %}
</neon:auth>
{% endif %}
    