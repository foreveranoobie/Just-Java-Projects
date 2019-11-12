<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "lc" uri = "/WEB-INF/localizer.tld"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/logout.tld"%>

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
<lc:Loc word="Table of courses which are not started" locale="${sessionScope.language}"/>
<table style="width:50%; margin-left:auto; margin-right:auto">
<tr>
<th>
<select name="subjectName" form="subjectSelect">
<option value="All"><lc:Loc word='All subjects' locale='${sessionScope.language}'/>
</option>
<c:forEach items="${requestScope.distCourse}" var="subj">
			 <option value="${subj}">${subj}</option>
</c:forEach>
</select>
<form id = "subjectSelect">
<button>OK</button>
<c:set var="subjects" value="${list}" scope="session"/>
<input type="hidden" name="distCourses" value="${requestScope.distCourse}" />
<input type="hidden" name="command" value="sortCourses" />
<input type="hidden" name="incommand" value="sortStudentSubjects" />
<input type="hidden" name="order" value="${requestScope.order}"/>
</form>
</th>

<th><lc:Loc word="Start date" locale="${sessionScope.language}"/></th>

<th><form id="longTerm" action="controller">
<c:set var="courses" value="${list}" scope="session"/>
<input type="hidden" name="command" value="sortCourses"/>
<input type="hidden" name="incommand" value="sortCoursesLong"/>
<input type="hidden" name="order" value="${requestScope.order}"/>
<input type="hidden" name="distCourses" value="${requestScope.distCourse}" /><button><lc:Loc word="End date" locale="${sessionScope.language}"/>
</button></form>
</th>

<th><form id="teacherSelect" action="controller">
<input type="hidden" name="command" value="studentCourses"/>
<input type="hidden" name="Coursecommand" value="preparingCourses"/>
<input type="hidden" name="incommand" value="getTeacherSubject"/>
<input type="hidden" name="distCourses" value="${requestScope.distCourse}" />
<select name="teacherNum" form="teacherSelect">
<option value="All"><lc:Loc word="All teachers" locale="${sessionScope.language}"/></option>
<c:forEach items="${list}" var="num">
<option value="${num.teacherId}">${num.teacherId}</option>
</c:forEach></select><button>OK</button></form></th>

<th><form id="popularSelect" action="controller">
<c:set var="courses" value="${list}" scope="session"/>
<input type="hidden" name="order" value="${requestScope.order}"/>
<input type="hidden" name="command" value="sortCourses"/>
<input type="hidden" name="incommand" value="sortPopularCourses"/>
<input type="hidden" name="distCourses" value="${requestScope.distCourse}" />
<button><lc:Loc word='Rating sort' locale='${sessionScope.language}'/>
</button></form></th>
</tr>
<c:forEach var="entry" items="${requestScope.list}">
  <tr>
  <td>${entry.subject}</td>
  <td>${entry.start}</td>
  <td>${entry.end}</td>
  <td>${entry.teacherLogin}</td>
  </tr>
</c:forEach>
</table>
</body>
</html>