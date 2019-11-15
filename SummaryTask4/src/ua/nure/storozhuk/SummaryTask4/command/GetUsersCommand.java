package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.SortedBase;
import ua.nure.storozhuk.SummaryTask4.sql.entity.AverageMark;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

/**
 * Admin receives the information about users
 *
 */
public class GetUsersCommand extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(GetUsersCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.getStatus().equals("admin")) {
        	request.setAttribute("errType", "You are not allowed for this request");
        	return "WEB-INF\\jsp\\error.jsp";
        }
		LOG.debug("GetUsersCommand started");
		if (request.getParameter("incommand") == null) {
			String forward = getUsers(request, response);
			LOG.debug("incommand == null. Finished");
			return forward;
		} else {
			String forward = "";
			try {
				Method method = this.getClass().getDeclaredMethod(request.getParameter("incommand"),
						HttpServletRequest.class, HttpServletResponse.class);
				forward = (String) method.invoke(this, request, response);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			LOG.debug("GetUsersCommand finished");
			return forward;
		}
	}

	/**
	 * Approves the teacher's registration
	 * 
	 * @param request
	 * @param response
	 */
	protected String approve(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
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
		LOG.debug("finished");
		return forward;
	}

	protected String unblockStudent(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
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
		LOG.debug("finished");
		return forward;
	}

	protected String blockStudent(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
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
		LOG.debug("finished");
		return forward;
	}

	public String getUsers(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			List<User> list = dbm.getUsers();
			request.getSession().setAttribute("usersList", list);
			List<User> registers = dbm.getRequestsReg();
			request.getSession().setAttribute("requestsList", registers);
			SortedBase sBase = new SortedBase();
			List<AverageMark> avMarks = sBase.getAvMark(request.getParameter("orderSelect"));
			request.setAttribute("averageMarks", avMarks);
			sBase.closeCon();
			dbm.closeCon();
			forward = "\\WEB-INF\\jsp\\admin\\usersList.jsp";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}
}
