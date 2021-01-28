<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        .error {color:red}
    </style>
    <title>Customer Registration Form</title>
</head>
<body>
    <form:form action="processForm" modelAttribute="customer" method="POST">
        First name (*): <form:input path="firstName" />
        <form:errors path="firstName" cssClass="error"/>
        <br><br>
        Last name (*): <form:input path="lastName"/>
        <form:errors path="lastName" cssClass="error"/>
        <br><br>
        Free passes (*): <form:input path="freePasses"/>
        <form:errors path="freePasses" cssClass="error"/>
        <br><br>
        Postal Code (*): <form:input path="postalCode"/>
        <form:errors path="postalCode" cssClass="error"/>
        <br><br>
        Course Code (*): <form:input path="courseCode"/>
        <form:errors path="courseCode" cssClass="error"/>
        <br><br>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>