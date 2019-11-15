<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "ex" uri = "WEB-INF/logout.tld"%>
<%@ taglib prefix = "lc" uri = "WEB-INF/localizer.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ex:Logout locale="${sessionScope.language}"/>
<br />
<a href="controller?command=changeLanguage">EN/RU</a>
<lc:Loc word="You are blocked" locale="${sessionScope.language}"/>!
</body>
</html>