<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "ex" uri = "/WEB-INF/logout.tld"%>
<%@ taglib prefix = "lc" uri = "/WEB-INF/localizer.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="${sessionScope.user.status}.jsp">
 <jsp:param name="status" value="${sessionScope.user.status}"/>
</jsp:include>
<table style="width:50%">
<tr>
  <th><lc:Loc word='Login' locale='${sessionScope.language}'/></th>
  <td>${sessionScope.user.login}</td>
</tr>
<tr>
  <th><lc:Loc word='Status' locale='${sessionScope.language}'/></th>
  <td>${sessionScope.user.status}</td>
</tr>
<tr>
  <th>ID</th>
  <td>${sessionScope.user.id}</td>
</tr>
</table>
</body>
</html>