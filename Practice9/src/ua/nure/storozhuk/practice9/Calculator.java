package ua.nure.storozhuk.practice9;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Calculator() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Operator oper = new Operator();
		response.setContentType("text/html");
		response.getWriter().print("<p>" + "First num is: " + request.getParameter("first") + "</p><p>Second num is: "
				+ request.getParameter("second") + "</p><p>The operation operand is: " + request.getParameter("operand")
				+ "</p><p>" + "The result of operation is: " + oper.calculate(request.getParameter("operand"),
						Integer.valueOf(request.getParameter("first")), Integer.valueOf(request.getParameter("second")))
				+ "</p>");
		response.getWriter().print("<a href=\"\">A link to initial form</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
