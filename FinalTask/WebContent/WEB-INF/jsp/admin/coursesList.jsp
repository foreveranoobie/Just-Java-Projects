<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
	<h3><lc:Loc word="Insert new course" locale="${sessionScope.language}"/></h3>
	<form name="courseInput" method="POST" action="controller">
		<input type="hidden" name="command" value="getCourses" /> <input
			type="hidden" name="incommand" value="createCourse" /> <input
			type="text" name="subject" value="<lc:Loc word="Subject" locale="${sessionScope.language}"/>" /> <input type="text"
			name="start" value="<lc:Loc word="Start date YYYY-MM-DD" locale="${sessionScope.language}"/>" /> <input type="text"
			name="end" value="<lc:Loc word="End date YYYY-MM-DD" locale="${sessionScope.language}"/>" /> <input type="text"
			name="teacherID" value="<lc:Loc word="Teacher ID" locale="${sessionScope.language}"/>" /> <input type="submit"
			name="button" value="<lc:Loc word="Create" locale="${sessionScope.language}"/>" />
	</form>
	<br />
	<table style="width: 70%">
		<tr>
			<th><lc:Loc word="Course ID" locale="${sessionScope.language}"/></th>
			<th><tags:table field="Subject" incommand="sortAdminCourses" order="${requestScope.order}" /></th>
			<th><lc:Loc word="Start date" locale="${sessionScope.language}"/></th>
			<th><tags:table field="End date" incommand="sortByTerms" order="${requestScope.order}" /></th>
			<th><form action="controller" id="teacherSelect">
			<button>OK</button>
			<input type="hidden" name="command" value="sortCourses" />
			<input type="hidden" name="incommand" value="teacherOrder" /> 
			</form>
			<select name="teacherNum" form="teacherSelect">
			<option value="All"><lc:Loc word="All teachers" locale="${sessionScope.language}"/></option>
			<c:forEach items="${requestScope.teachersID}" var="num">
			 <option value="${num}">${num}</option>
			</c:forEach>
			</select></th>
			<th>
			<form method="POST" action="controller">
			<input type="hidden" name="command" value="sortCourses" />
			<input type="hidden" name="incommand" value="sortPopulars" /> 
			<input type="hidden" name="order" value="${requestScope.order}"/>
			<button><lc:Loc word="Sort courses by students count" locale="${sessionScope.language}"/>
			</button>
			</form>
			</th>
		</tr>
		<c:forEach items="${requestScope.courses}" var="item">
			<tr>
				<td>${item.id}</td>
				<td>${item.subject}</td>
				<td>${item.start}</td>
				<td>${item.end}</td>
				<td>${item.teacherId}</td>
				<td style="border: none"><form name="ChangeForm" method="GET"
						action="controller">
						<c:set var="course" scope="session">${item.id}, ${item.subject}, ${item.start},
 ${item.end}, ${item.teacherId}</c:set>
						<c:out value="${requestScope.enablebtnRelatedDocs }" />
						<input type="hidden" name="command" value="getCourses" />
						<input type="hidden" name="incommand" value="getUpdateList" /> 
						<button><lc:Loc word="Update" locale="${sessionScope.language}"/></button>
					</form></td>
				<td>
					<form name="deleteForm" method="POST" action="controller">
						<input type="hidden" name="id" value="${item.id}" /> 
						<input type="hidden" name="command" value="getCourses" />
						<input type="hidden" name="incommand" value="deleteCourse" />
					    <button><lc:Loc word="Delete" locale="${sessionScope.language}"/></button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>