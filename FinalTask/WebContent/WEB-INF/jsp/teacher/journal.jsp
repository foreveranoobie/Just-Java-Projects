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
<br />
	<p><lc:Loc word="Journal of finished courses" locale="${sessionScope.language}"/></p>
	<table style="width: 35%">
       <tr>
          <th><form action="controller" id="courseSorter">
          <input type="hidden" name="command" value="getJournal" />
          <input type="hidden" name="incommand" value="getCurrentCourses" />
          <input type="hidden" name="toSort" value="getSortedJournal" />
          <input type="hidden" name="order" value="${requestScope.order}" />
          <select name="subjectSort" form="courseSorter">
          <option value="All"><lc:Loc word="All subjects" locale="${sessionScope.language}"/></option>
          <c:forEach items="${requestScope.subjects}" var="subs">
          <option value="${subs}">${subs}</option>          
          </c:forEach>
          </select>
          <button>OK</button></form></th>
          <th><lc:Loc word="Student ID" locale="${sessionScope.language}"/></th>
          <th><lc:Loc word="Mark" locale="${sessionScope.language}"/></th>
       </tr>
       <c:forEach items="${requestScope.journal}" var="item">
       <tr>
       <td>${item.subject}</td> 
       <td>${item.studentId}</td> 
       <td>${item.mark}</td> 
       </tr>
       </c:forEach>
    </table>
    <br/>
    <p><lc:Loc word="Journal of current courses" locale="${sessionScope.language}"/></p>
	<table style="width: 35%">
       <tr>
          <th><form action="controller" id="subjSorter">
          <input type="hidden" name="command" value="getJournal" />
          <input type="hidden" name="incommand" value="getCurrentCourses" />
          <input type="hidden" name="toSort" value="getSortedCourses" />
          <input type="hidden" name="order" value="${requestScope.order}" />
          <select name="subjectSort" form="subjSorter">
          <option value="All"><lc:Loc word="All subjects" locale="${sessionScope.language}"/></option>
          <c:forEach items="${requestScope.subjects}" var="subs">
          <option value="${subs}">${subs}</option>          
          </c:forEach>
          </select>
          <button>OK</button></form></th>
          <th><lc:Loc word="Student ID" locale="${sessionScope.language}"/></th>
          <th><lc:Loc word="Course status" locale="${sessionScope.language}"/></th>
       </tr>
       <c:forEach items="${requestScope.courses}" var="item">
       <tr>
       <td>${item.subject}</td> 
       <td>${item.studentId}</td> 
       <td>${item.status}</td>
       <c:if test="${item.status == 'finished'}">
       <td><form name="SetMark" method="POST" action="controller">
       <lc:Loc word="Enter mark from 1-5" locale="${sessionScope.language}"/>
       <input type="number" name="mark"/>
       <input type="hidden" name="command" value="getJournal" />
       <input type="hidden" name="incommand" value="setMark" />
       <c:set var="toMove" scope="session">${item.courseId}, ${item.studentId}</c:set>
       <input type="submit" name="buttOut" value="Write to journal" />
       </form>
       </td> 
       </c:if>
       </tr>
       </c:forEach>
    </table>
</body>
</html>