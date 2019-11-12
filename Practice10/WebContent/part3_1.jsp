<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty sessionScope.role}">
Hello, ${sessionScope.login }
<br/>
<a href="controller?command=logout">Logout</a><br/>
</c:if>
<c:forEach items="${users}" var="item">
    ${item}<br>
</c:forEach>
<a href="part3.jsp">Back to filling form</a>
</body>
</html>