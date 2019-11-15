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
<div style="float: left">
<table style="height:150px; overflow:auto; border: 1px solid black; 
border-collapse: collapse; margin-left:5px; margin-right:auto">
<tr style="border: 1px solid black">
   <th>User id</th>
   <th>User login</th>
   <th>User password</th>
   <th>User status</th>
</tr>
<c:forEach items="${usersList}" var="item">
<tr style="border: 1px solid black">
<c:choose>
   <c:when test="${ item.status == 'student' }">
   <td>${item.id}</td>
   <td>${item.login}</td>
   <td>${item.password}</td>
   <td><lc:Loc word="${item.status}" locale="${sessionScope.language}"/></td>
   <td style="border: none"><form name="BlockForm" method="POST" action="controller">
   <input type="hidden" name="command" value="getUsers" /> 
   <input type="hidden" name="incommand" value="blockStudent" />
   <input type="hidden" name="id" value="${item.id}"/> 
   <input type="hidden" name="status" value="${item.status}"/> 
   <input type="submit" value="<lc:Loc word="Block" locale="${sessionScope.language}"/>"/>
   </form>
   </td>
   </c:when>
   <c:when test="${ item.status == 'studentBlocked' }">
   <td>
   ${item.id}
   </td>
   <td>${item.login}</td>
   <td>${item.password}</td>
   <td><lc:Loc word="${item.status}" locale="${sessionScope.language}"/></td>
   <td style="border: none"><form name="UnblockForm" method="POST" action="controller">
   <input type="hidden" name="id" value="${item.id}"/> 
   <input type="hidden" name="status" value="${item.status}"/>
   <input type="hidden" name="command" value="getUsers" /> 
   <input type="hidden" name="incommand" value="unblockStudent" />
   <input type="submit" value="<lc:Loc word="Unblock" locale="${sessionScope.language}"/>"/>
   </form>
   </td>
   </c:when>
   <c:when test="${item.status != 'student' and item.status != 'studentBlocked'}">
   <td>${item.id}</td>
   <td>${item.login}</td>
   <td>${item.password}</td>
   <td><lc:Loc word="${item.status}" locale="${sessionScope.language}"/></td>
   </c:when>
</c:choose>
</tr>
</c:forEach>
</table>
</div>
<c:if test="${not empty requestsList}">
<div style="float: left">
<table style="height:auto; overflow:auto; border: 1px solid black; 
border-collapse: collapse; margin-left:5px; margin-right:auto">
<tr style="border: 1px solid black">
   <th>Requested login</th>
   <th>Requested password</th>
</tr>
<c:forEach items="${requestsList}" var="item">
<tr style="border: 1px solid black">
   <td>${item.login}</td>
   <td>${item.password}</td>
   <td>
   <form name="ApproveForm" method="POST" action="controller">
   <input type="hidden" name="command" value="getUsers" />
   <input type="hidden" name="incommand" value="approve" />
   <input type="hidden" name="login" value="${item.login}"/>
   <input type="hidden" name="password" value="${item.password}"/>
   <input type="submit" name="buttApp" value="Approve" />
   </form>
   </td>
</tr>
</c:forEach>
</table>
</div>
</c:if>
<table style="width:50%;  border: 1px solid black">
<tr style="border: 1px solid black">
   <th style="border: 1px solid black">ID</th>
   <th style="border: 1px solid black"><lc:Loc word="Login" locale="${sessionScope.language}"/></th>
   <th style="border: 1px solid black"><lc:Loc word="AVG Mark" locale="${sessionScope.language}"/></th>
   <th><form action="controller" id="sortMarks">
   <input type="hidden" name="command" value="getUsers" /> 
   <input type="hidden" name="incommand" value="getUsers" /> 
   <select name="orderSelect" form="sortMarks">
   <option value="asc"><lc:Loc word="Ascending" locale="${sessionScope.language}"/></option>
   <option value="desc"><lc:Loc word="Descending" locale="${sessionScope.language}"/></option>
   </select>
   <button><lc:Loc word="Sort" locale="${sessionScope.language}"/></button>
   </form></th>
</tr>
<c:forEach items="${requestScope.averageMarks}" var="avgMarks">
<tr style="border: 1px solid black">
   <td style="border: 1px solid black">${avgMarks.studentId}</td>
   <td style="border: 1px solid black">${avgMarks.login}</td>
   <td style="border: 1px solid black">${avgMarks.mark}</td>
</tr>
</c:forEach>
</table>
</body>
</html>