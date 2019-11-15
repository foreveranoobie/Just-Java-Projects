<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "lc" uri = "WEB-INF/localizer.tld"%>
<%@ taglib prefix = "ex" uri = "WEB-INF/logout.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="controller?command=changeLanguage">EN/RU</a>
<br/>
<br/>
<ex:Logout locale="${sessionScope.language}"/>
<br/>
<table style="width:80%; margin-left:auto; margin-right:auto">
<tr>
  <td>
  <a href="controller?command=getJournal"><lc:Loc word="My courses" locale="${sessionScope.language}"/></a>
  </td>
  <td>
  <a href="userInfo.jsp"><lc:Loc word="User information" locale="${sessionScope.language}"/></a>
  </td>
</tr>
</table>
<lc:Loc word="Welcome" locale="${sessionScope.language}"/>, ${ user.login }
</body>
</html>