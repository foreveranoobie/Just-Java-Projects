package ua.nure.storozhuk.SummaryTask4;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.storozhuk.SummaryTask4.command.Command;
import ua.nure.storozhuk.SummaryTask4.command.CommandContainer;
import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Controller.class);

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("doGet of controller started");
		request.setCharacterEncoding("UTF-8");
		String commandName = request.getParameter("command");
		Command command = CommandContainer.get(commandName);
		String forward = command.execute(request, response);
		if (forward.equals("redirectBack")) {
			response.sendRedirect(request.getHeader("referer"));
			LOG.debug("doGet redirect");
		} else if (!forward.equals("redirect")) {
			request.getRequestDispatcher(forward).forward(request, response);
			LOG.debug("doGet forward");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("doPost of controller started");
		request.setCharacterEncoding("UTF-8");
		String commandName = request.getParameter("command");
		Command command = CommandContainer.get(commandName);
		String forward = command.execute(request, response);
		if (forward.equals("redirectBack")) {
			response.sendRedirect(request.getHeader("referer"));
			LOG.debug("doPost redirect");
		} else if (!forward.equals("redirect")) {
			request.getRequestDispatcher(forward).forward(request, response);
			LOG.debug("doPost forward");
		}
	}
}
