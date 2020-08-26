<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<html>
<head>
</head>
<body>
<form:form action="register" modelAttribute="captchaDescriptor" method="POST" id="loginForm">
      <label for="captchaCode">Retype the characters from the picture:</label>
      <botDetect:captcha id="captchaDescriptor" userInputID="captchaCode" />
      <input name="captchaCode" type="text" id="captchaCode" value="${captchaDescriptor.captchaCode}" />
      <br/>
      Логин:<input name="login" type="text" value="${user.login}"/>
      <br/>
      Пароль:<input name="password" type="password" value="${user.password}"/>
      <br/>
      Подтвердите пароль:<input name="passwordConfirm" type="password" value="${user.passwordConfirm}"/>
      <br/>
      E-Mail:<input name="email" type="text" value="${user.email}"/>
      <br/>
      <input type="submit" value="Зарегистрироваться"/>
</form:form>
</body>
</html>