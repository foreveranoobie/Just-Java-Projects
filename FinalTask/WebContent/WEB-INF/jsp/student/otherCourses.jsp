<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
<lc:Loc word='Table of courses which aren\'t applied by you' locale='${sessionScope.language}'/>
<table style="width:50%; margin-left:auto; margin-right:auto">
<tr>
<th><select name="subjectName" form="subjectSelect">
<option value="All"><lc:Loc word='All subjects' locale='${sessionScope.language}'/></option>
<c:forEach items="${requestScope.distCourse}" var="subj">
			 <option value="${subj}">${subj}</option>
</c:forEach>
</select>
<form id = "subjectSelect">
<button>OK</button>
<c:set var="subjects" value="${list}" scope="session"/>
<input type="hidden" name="distCourses" value="${requestScope.distCourse}" />
<input type="hidden" name="command" value="studentCourses" />
<input type="hidden" name="Coursecommand" value="otherCourses"/>
<input type="hidden" name="incommand" value="sortStudentCourses" />
<input type="hidden" name="order" value="${requestScope.order}"/>
</form></th>

<th><lc:Loc word='Start date' locale='${sessionScope.language}'/></th>

<th><form id="longTerm" action="controller">
<c:set var="courses" value="${list}" scope="session"/>
<input type="hidden" name="command" value="studentCourses"/>
<input type="hidden" name="Coursecommand" value="otherCourses"/>
<input type="hidden" name="incommand" value="sortCoursesLong" />
<input type="hidden" name="order" value="${requestScope.order}"/>
<input type="hidden" name="distCourses" value="${requestScope.distCourse}" /><button><lc:Loc word='End date' locale='${sessionScope.language}'/></button></form>
</th>

<th><form id="teacherSelect" action="controller">
<input type="hidden" name="command" value="studentCourses"/>
<input type="hidden" name="Coursecommand" value="otherCourses"/>
<input type="hidden" name="incommand" value="getTeacherSubject"/>
<input type="hidden" name="distCourses" value="${requestScope.distCourse}" />
<select name="teacherNum" form="teacherSelect">
<option value="All"><lc:Loc word="All teachers" locale="${sessionScope.language}"/></option>
<c:forEach items="${teacherID}" var="num">
<option value="${num}">${num}</option>
</c:forEach></select><button>OK</button></form></th>

<th><form id="popularSelect" action="controller">
<c:set var="courses" value="${list}" scope="session"/>
<input type="hidden" name="order" value="${requestScope.order}"/>
<input type="hidden" name="command" value="studentCourses"/>
<input type="hidden" name="Coursecommand" value="otherCourses"/>
<input type="hidden" name="incommand" value="sortPopularCourses"/>
<input type="hidden" name="distCourses" value="${requestScope.distCourse}" />
<button><lc:Loc word='Rating sort' locale='${sessionScope.language}'/></button></form></th>
</tr>
<c:forEach var="entry" items="${list}">
  <tr>
  <td>${entry.subject}</td>
  <td>${entry.start}</td>
  <td>${entry.end}</td>
  <td>${entry.teacherLogin}</td>
  <td>
  <form method="POST" action="controller">
  <c:set var="toMove" scope="session">${entry.subject}, ${entry.teacherLogin}</c:set>
  <input type="hidden" name="command" value="studentCourses" /> 
  <input type="hidden" name="Coursecommand" value="applyStudent" /> 
  <input type="submit" value="<lc:Loc word='Apply' locale='${sessionScope.language}'/>"/>
  </form>
  </td>
  </tr>
</c:forEach>
</table>
</body>
</html>