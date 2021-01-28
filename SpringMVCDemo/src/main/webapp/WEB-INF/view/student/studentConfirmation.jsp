 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
<html>
<head>
    <title>Student Confirmation</title>
</head>
<body>
    Student ${student.firstName} ${student.lastName} from ${student.country} has been successfully confirmed.
    Programming language is ${student.programmingLanguage}<br>
    Also you are familiar with OS:
    <ul>
        <c:forEach var="temp" items="${student.operatingSystems}">
            <li>${temp}</li>
        </c:forEach>
    </ul>
</body>
</html>