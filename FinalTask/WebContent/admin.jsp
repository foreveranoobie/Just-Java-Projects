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
<lc:Loc word="Welcome" locale="${sessionScope.language}"/>, ${ user.login }
<br />
<a href="controller?command=changeLanguage">EN/RU</a>
<table style="width:80%; margin-left:auto; margin-right:auto">
<tr>
  <td>
  <a href="controller?command=getCourses"><lc:Loc word="Courses list" locale="${sessionScope.language}"/></a>
  </td>
  <td>
  <a href="controller?command=getUsers"><lc:Loc word="Users list" locale="${sessionScope.language}"/></a>
  </td>
  <td>
  <a href="userInfo.jsp"><lc:Loc word="User information" locale="${sessionScope.language}"/></a>
  </td>
</tr>
</table>
<ex:Logout locale="${sessionScope.language}"/>
</body>
</html>