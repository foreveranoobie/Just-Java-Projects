<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<html>
<head>
</head>
<body>
<form:form action="login" modelAttribute="captchaDescriptor" method="POST" id="form1">
      <label for="captchaCode">Retype the characters from the picture:</label>
      <botDetect:captcha id="captchaDescriptor" userInputID="captchaCode" />
      <input name="captchaCode" type="text" id="captchaCode" value="${captchaDescriptor.captchaCode}" />
</form:form>
</body>
</html>