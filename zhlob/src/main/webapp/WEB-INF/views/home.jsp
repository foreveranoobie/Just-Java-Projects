<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
</head>
<body>
<h2>Hello World!</h2>
<br/>
Current time is <span th:text="${serverTime}"/>
</body>
</html>
