<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix = "ex" uri = "WEB-INF/logout.tld"%>
<%@ taglib prefix = "lc" uri = "WEB-INF/localizer.tld"%>
<html>
<body>
  <a href="controller?command=changeLanguage">EN/RU</a>
	<c:choose>
		<c:when test="${empty user.status}">
		<table style="width:40%; margin-left:auto; margin-right:auto">
		    <tr>
		    <td>
		    <form name="LoginForm" method="POST" action="controller">
		    <input type="hidden" name="command" value="loginUser" />
				<lc:Loc word="Login" locale="${sessionScope.language}"/>:<br /> 
				<input type="text" name="login" value="" /> <br /><lc:Loc word="Password" locale="${sessionScope.language}"/>:<br />
				<input type="password" name="password" value="" /> <br />
				<button><lc:Loc word="Log In" locale="${sessionScope.language}"/></button>
		    </form>
			</td>		
			<td>
			<lc:Loc word="If you don't have an account" locale="${sessionScope.language}"/>
			<form action="register.jsp">
            <input type="submit" value="<lc:Loc word="Sign Up" locale="${sessionScope.language}"/>" />
            </form> 
			</td>
			</tr>
		</table>
		</c:when>
		<c:when test="${user.status == 'waiting'}">
           <lc:Loc word="Your request for a teacher position is in processing" locale="${sessionScope.language}"/>
           <ex:Logout locale="${sessionScope.language}"/>
		</c:when>
		<c:when test="${not empty user.status and user.status != 'waiting'}">
           <c:redirect url = "http://localhost:8080/FinalTask/${user.status}.jsp"/>
		</c:when>
	</c:choose>
</body>
</html>