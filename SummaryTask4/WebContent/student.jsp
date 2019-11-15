<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "lc" uri = "WEB-INF/localizer.tld"%>
<%@ taglib prefix = "ex" uri = "WEB-INF/logout.tld"%>
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
  <a href="controller?command=studentCourses&Coursecommand=preparingCourses"><lc:Loc word="Courses in preparing" locale="${sessionScope.language}"/></a>
  </td>
  <td>
  <a href="controller?command=studentCourses&Coursecommand=progressCourses"><lc:Loc word="Courses in progress" locale="${sessionScope.language}"/></a>
  </td>
  <td>
  <a href="controller?command=studentCourses&Coursecommand=finishedCourses"><lc:Loc word="Finished courses" locale="${sessionScope.language}"/></a>
  </td>
  <td>
  <a href="controller?command=studentCourses&Coursecommand=otherCourses"><lc:Loc word="Other courses" locale="${sessionScope.language}"/></a>
  </td>
  <td>
  <a href="userInfo.jsp"><lc:Loc word="User information" locale="${sessionScope.language}"/></a>
  </td>
</tr>
</table>
<ex:Logout locale="${sessionScope.language}"/>
</body>
</html>