<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/logout.tld"%>
<%@ taglib prefix = "lc" uri = "/WEB-INF/localizer.tld"%>
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
	<br />
	<p><lc:Loc word="Update course with ID" locale="${sessionScope.language}"/>: ${requestScope.course.id}</p>
	<form name="logout" method="POST" action="controller">
		<input type="text" name="subject"
			value="${requestScope.course.subject}"> <input type="text"
			name="start" value="${requestScope.course.start}"> <input
			type="text" name="end" value="${requestScope.course.end}"> <input
			type="text" name="teacherID" value="${requestScope.course.teacherId}">
			<input type="hidden" name="command" value="getCourses" />
		<input type="hidden" name="incommand" value="updateCourse" /> <input
			type="hidden" name="id" value="${ requestScope.course.id }">
		<input type="submit" name="buttOut" value="<lc:Loc word="Apply" locale="${sessionScope.language}"/>" />
	</form>
</body>
</html>