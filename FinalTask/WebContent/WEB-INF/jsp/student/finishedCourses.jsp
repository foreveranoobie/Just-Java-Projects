<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/logout.tld"%>
<%@ taglib prefix = "lc" uri = "/WEB-INF/localizer.tld"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="//${sessionScope.user.status}.jsp">
 <jsp:param name="status" value="${sessionScope.user.status}"/>
</jsp:include>
<br/>
<lc:Loc word='Table of courses done by student' locale="${sessionScope.language}"></lc:Loc><br />
<table style="width:50%">
<tr>
<th><form id="courseForm" action="controller">
<input type="hidden" name="command" value="studentCourses"/>
<input type="hidden" name="Coursecommand" value="finishedCourses"/>
<input type="hidden" name="incommand" value="sortedFinishedCourses"/>
<input type="hidden" name="order" value="${requestScope.order}"/>
<select name="subjSelect" form="courseForm">
<option value="All"><lc:Loc word='Course' locale='${sessionScope.language}'></lc:Loc></option>
<c:forEach var="subs" items="${subjects}">
<option value="${subs}">${subs}</option>
</c:forEach>
</select><button>OK</button></form></th>
<th><lc:Loc word='Mark' locale='${sessionScope.language}' /></th>
</tr>
<c:forEach var="entry" items="${journal}">
<tr>
  <td>${entry.subject}</td>
  <td>${entry.mark}</td>
</tr>
</c:forEach>
</table>
</body>
</html>