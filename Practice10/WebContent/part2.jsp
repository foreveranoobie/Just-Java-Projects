<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
  table{
      border-collapse: collapse;
  }
  table, td, tr, th{
    border: 1px solid black;
  }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty sessionScope.role}">
Hello, ${sessionScope.login }
<br/>
<a href="controller?command=logout">Logout</a><br/>
</c:if>
<table style="width: 50%; margin: auto; text-align: center">
<tr>
<td>&nbsp;</td>
<c:forEach var="i" begin="1" end="10">
    <th>${i}</th>
</c:forEach>
<c:forEach var="outter" begin="1" end="10">
    <tr>
    <th>${outter}</th>
    <c:forEach var="inner" begin="1" end="10">
       <td>${inner*outter}</td>
    </c:forEach>
    </tr>
</c:forEach>
</tr>
</table>
<br/>
<a href="part3.jsp">Part3</a>
</body>
</html>