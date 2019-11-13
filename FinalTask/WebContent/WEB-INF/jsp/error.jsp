<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error page</title>
</head>
<body>
Error: <%= request.getAttribute("errType") %>
<a href="index.jsp">To main page</a>
</body>
</html>