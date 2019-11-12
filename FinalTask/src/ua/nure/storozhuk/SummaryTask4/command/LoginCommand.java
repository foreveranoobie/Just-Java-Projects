package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

public class LoginCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			User user = dbm.getUserStatus(request.getParameter("login"), request.getParameter("password"));
			if (user == null) {
				request.setAttribute("errType", new String("User not found"));
				forward = "\\WEB-INF\\jsp\\error.jsp";
			} else {
				request.getSession().setAttribute("user", user);
				if (user.getStatus().equals("waiting")) {
					response.sendRedirect("index.jsp");
					forward = "redirect";
				} else {
					forward = "redirect";
					response.sendRedirect(user.getStatus() + ".jsp");
				}
				dbm.closeCon();
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
