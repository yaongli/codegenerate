<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/admin/layouts/neonDefaultLayouts.jsp" flush="true">
    <tiles:put name="header" value="/admin/common/header.jsp"/>
    <tiles:put name="left-menu" value="/admin/common/leftmenu.jsp"/>
    <tiles:put name="body-content" value="{{ jsp.bodyfile }}" />
    <tiles:put name="right-menu" value="/admin/common/rightmenu.jsp"/>
    <tiles:put name="footer" value="/admin/common/footer.jsp"/>
</tiles:insert>
