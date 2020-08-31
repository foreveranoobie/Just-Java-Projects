<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
</head>
<body>
<sec:authorize access="isAuthenticated()">
  <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
  <form:form method="POST" action="/login">
    <h2>Вход в систему</h2>
    <div>
      <input name="username" type="text" placeholder="Username"
             autofocus="true"/>
      <input name="password" type="password" placeholder="Password"/>
      <button type="submit">Log In</button>
      <h4><a href="auth/register">Зарегистрироваться</a></h4>
    </div>
  </form:form>
</div>
</body>
</html>