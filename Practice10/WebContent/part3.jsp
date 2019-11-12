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
<form name="nameForm" method="POST" action="controller">
  Name:<br /> <input type="text" name="login" value="" /> 
  <input type="submit" name="doFunction" value="Log in" />
</form>
<a href="controller?method=remove">Remove users list</a>
<br/>
<a href="part4.jsp">To part4 form</a>
</body>
</html>