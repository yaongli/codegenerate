<%@taglib prefix="vlh" uri="/WEB-INF/valuelist.tld"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jstl/core" %>

<vlh:root configName="reportLook" value="valueList" url="/np{{ action.list }}.do?" includeParameters="*">
    <table class="reportLook">
        <vlh:row bean="report" nlCache="id" attributes2="">
{% for column in columnList %}
            <vlh:column title="{{ column.title }}" property="{{ column.name }}" sortable="asc" attributes="width='250'" {% if column.format %} format="{{ column.format }}" {% endif %} {% if column.mapping %} mapping="{{ column.mapping }}" {% endif %} />
{% endfor %}
        </vlh:row>
    </table>
    <vlh:paging pages="5" showSummary="true"> <c:out value="${page}" />&nbsp;</vlh:paging>
</vlh:root>
