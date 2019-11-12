<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
  table{
      border-collapse: collapse;
  }
  table, td, tr, th{
    border: 1px solid black;
  }
</style>
<title>Insert title here</title>
</head>
<body>
<% if(session.getAttribute("role")!=null){
	out.println("<p>Welcome,"+session.getAttribute("login")+"</p>");
	out.println("<a href=\"controller?command=logout\">Logout</a><br/>");
} %>
<br/>
	<%
		int nums[] = new int[10];
		for (int m = 0; m < 10; m++) {
			nums[m] = m + 1;
		}
	%>
	<table style="width: 50%; margin:auto; text-align:center">
		<tr>
			<%
				out.println("<th>&nbsp;</th>");
				for (int m = 1; m < 11; m++) {
					out.println("<th>" + m + "</th>");
				}
			%>
		</tr>
		<%
			for (int m = 0; m < 10; m++) {
				out.println("<tr>");
                out.println("<th>"+(m+1)+"</th>");
                for(int inner = 0; inner < 10; inner++){
                	out.println("<td>"+((m+1)*nums[inner])+"</td>");
                }
                out.println("</tr>");
			}
		%>
	</table>
<br/>
<a href="part2.jsp">Part2</a>
</body>
</html>