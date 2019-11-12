package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

public class SignUpCommand extends Command{

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			String status = request.getParameter("status");
			switch(status) {
				case "Преподаватель":
					status = "teacher";
					break;
				case "Студент":
					status = "student";
					break;
				default:
					break;
			}
			User user = dbm.registerUser(status, request.getParameter("login"),
					request.getParameter("password"));
			request.getSession().setAttribute("user", user);
			if (user.getStatus().equals("student")) {
				forward = "redirect";
				response.sendRedirect("student.jsp");
			} else {
				forward = "redirect";
				response.sendRedirect("index.jsp");
			}
			dbm.closeCon();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
