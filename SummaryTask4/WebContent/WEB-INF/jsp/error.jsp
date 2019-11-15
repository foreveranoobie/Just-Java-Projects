<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "lc" uri = "/WEB-INF/localizer.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error page</title>
</head>
<body>
 <lc:Loc word="Error" locale="${sessionScope.language}"/>: <lc:Loc word="${requestScope.errType}" locale="${sessionScope.language}"/>
<a href="index.jsp">To main page</a>
</body>
</html>