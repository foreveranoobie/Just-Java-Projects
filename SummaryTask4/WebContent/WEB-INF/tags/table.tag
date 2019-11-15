<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/logout.tld"%>
<%@ taglib prefix = "lc" uri = "/WEB-INF/localizer.tld"%>

<%@attribute name="field" required="true"  %>
<%@attribute name="order" required="true"  %>
<%@attribute name="incommand" required="true"  %>
<form action="controller">
<c:set var="list" value="${requestScope.courses}" scope="session" />
<input type="hidden" name="command" value="sortCourses"/>
<input type="hidden" name="incommand" value="${pageScope.incommand}"/>
<input type="hidden" name="order" value="${pageScope.order}"/>
<button><lc:Loc word="${field}" locale="${sessionScope.language}"/></button>
</form>