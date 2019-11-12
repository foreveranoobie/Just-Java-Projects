<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="nameForm" method="POST" action="controller">
  <input type="hidden" name="command" value="Login" />
  Login:<br /> <input type="text" name="login" value="" /><br/>
  Password:<br /> <input type="password" name="password" value="" /> 
  <input type="submit" name="doFunction" value="Log in" />
</form>
</body>
</html>