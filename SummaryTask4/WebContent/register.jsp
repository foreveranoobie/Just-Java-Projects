<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "lc" uri = "WEB-INF/localizer.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <a href="controller?command=changeLanguage">EN/RU</a>
		<form name="RegForm" method="POST" action="controller">
		<lc:Loc word="Login" locale="${sessionScope.language}"></lc:Loc><br /> 
		<input type="text" name="login" value="" /> 
		<br /><lc:Loc word="Password" locale="${sessionScope.language}"></lc:Loc>:<br />
		<input type="password" name="password" value="" /> <br />
		<br /><lc:Loc word="Status to register" locale="${sessionScope.language}"></lc:Loc>:<br />
		<select name="status">
		  <option><lc:Loc word="student" locale="${sessionScope.language}"></lc:Loc></option>
		  <option><lc:Loc word="teacher" locale="${sessionScope.language}"></lc:Loc></option>
		</select>
		<input type="hidden" name="command" value="signUser" />
		<input type="submit" name="register" value="<lc:Loc word="Sign Up" locale="${sessionScope.language}"></lc:Loc>" />
		</form>
</body>
</html>