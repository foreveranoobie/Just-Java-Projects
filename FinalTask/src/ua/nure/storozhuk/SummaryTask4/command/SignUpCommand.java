package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;
/**
 * Command required for user registration
 *
 */
public class SignUpCommand extends Command{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		boolean spaces = request.getParameter("login").contains(" ") || request.getParameter("password").contains(" ");
		boolean empty = request.getParameter("login").isEmpty() || request.getParameter("password").isEmpty();
		if(spaces || empty) {
			LOG.error("Login or password fields are incorrect");
			LOG.trace("Registration login or password are incorrect");
			request.setAttribute("errType", "Login or password contains are incorrect");
			return "WEB-INF\\jsp\\error.jsp";
		}
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
		LOG.debug("finished");
		return forward;
	}

}
