<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Registration Form</title>
</head>
<body>
    <form:form action="processForm" modelAttribute="student" method="POST">
        First name: <form:input path="firstName" />
        <br><br>
        Last name: <form:input path="lastName"/>
        <br><br>
        Country:
        <form:select path="country">
            <form:options items="${student.countries}"/>
        </form:select>
        <br><br>
        Choose your programming language:
        <br>
        Java <form:radiobutton path="programmingLanguage" value="Java" />
        PHP <form:radiobutton path="programmingLanguage" value="PHP" />
        C# <form:radiobutton path="programmingLanguage" value="C#" />
        Ruby <form:radiobutton path="programmingLanguage" value="Ruby" />
        <br><br>
        Choose operating systems you are experienced with:
        <br>
        Linux <form:checkbox path="operatingSystems" value="Linux" />
        Mac OS <form:checkbox path="operatingSystems" value="Mac OS" />
        OS Windows <form:checkbox path="operatingSystems" value="OS Windows" />
        <br><br>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>