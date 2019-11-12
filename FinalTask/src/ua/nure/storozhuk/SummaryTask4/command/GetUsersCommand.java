package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

public class GetUsersCommand extends Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("incommand") == null) {
			String forward = getUsers(request, response);
			return forward;
		} else {
			String forward = "";
			try {
				Method method = this.getClass().getDeclaredMethod(request.getParameter("incommand"),
						HttpServletRequest.class, HttpServletResponse.class);
				forward = (String) method.invoke(this, request, response);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			return forward;
		}
	}
	
	protected String approve(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			dbm.approveTeacher(request.getParameter("login"), request.getParameter("password"));
			dbm.closeCon();
			forward = "redirect";
			response.sendRedirect(request.getHeader("referer"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

	protected String unblockStudent(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			int id = Integer.valueOf(request.getParameter("id"));
			String status = request.getParameter("status");
			if (dbm.unblockStudent(id, status)) {
				forward = getUsers(request, response);
			}
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return forward;
	}

	protected String blockStudent(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			int id = Integer.valueOf(request.getParameter("id"));
			String status = request.getParameter("status");
			if (dbm.blockStudent(id, status)) {
				forward = getUsers(request, response);
			}
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return forward;
	}

	public String getUsers(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			List<User> list = dbm.getUsers();
			request.getSession().setAttribute("usersList", list);
			List<User> registers = dbm.getRequestsReg();
			request.getSession().setAttribute("requestsList", registers);
			dbm.closeCon();
			forward = "\\WEB-INF\\jsp\\admin\\usersList.jsp";
			// request.getRequestDispatcher("\\WEB-INF\\jsp\\admin\\usersList.jsp").forward(request,
			// response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return forward;
	}
}
