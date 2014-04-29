<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/admin/layouts/neonValueListLayouts.jsp" flush="true">
    <tiles:put name="title" value="{{ jsp.title }}" />
    <tiles:put name="searchBody" value="{{ jsp.listsearchfile }}" />
    <tiles:put name="titleBar" value="{{ jsp.listtoolbarfile }}" />
    <tiles:put name="valueListBody" value="{{ jsp.listbodyfile }}" />
</tiles:insert>
