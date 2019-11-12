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

import ua.nure.storozhuk.SummaryTask4.command.Command;
import ua.nure.storozhuk.SummaryTask4.command.CommandContainer;
import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String commandName = request.getParameter("command");
		Command command = CommandContainer.get(commandName);
		String forward = command.execute(request, response);
		if (forward.equals("redirectBack")) {
			response.sendRedirect(request.getHeader("referer"));
		} else if (!forward.equals("redirect")) {
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String commandName = request.getParameter("command");
		Command command = CommandContainer.get(commandName);
		String forward = command.execute(request, response);
		if (forward.equals("redirectBack")) {
			response.sendRedirect(request.getHeader("referer"));
		} else if (!forward.equals("redirect")) {
			request.getRequestDispatcher(forward).forward(request, response);
		}
		/*
		 * Method method; try { method =
		 * this.getClass().getDeclaredMethod(request.getParameter("command"),
		 * HttpServletRequest.class, HttpServletResponse.class); method.invoke(this,
		 * request, response); } catch (NoSuchMethodException | SecurityException |
		 * IllegalAccessException | IllegalArgumentException | InvocationTargetException
		 * e) { e.printStackTrace(); }
		 */

	}

/*//Reallocated
	protected void createCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			DBManager dbm = new DBManager();
			String subject = request.getParameter("subject");
			int teacherId = Integer.valueOf(request.getParameter("teacherID"));
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			Course course = new Course(0, subject, start, end, teacherId);
			dbm.createCourse(course);
			getCourses(request, response);
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

//Reallocated
	protected void deleteCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			DBManager dbm = new DBManager();
			dbm.deleteCourse(Integer.valueOf(request.getParameter("id")));
			dbm.closeCon();
			getCourses(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		;
	}

	// Reallocated
	protected void getUpdateList(HttpServletRequest request, HttpServletResponse response) {
		String[] list = (String[]) request.getSession().getAttribute("course").toString().split(",\\s");
		request.getSession().removeAttribute("course");
		Course course = new Course(Integer.valueOf(list[0]), list[1], list[2], list[3], Integer.valueOf(list[4]));
		request.setAttribute("course", course);
		try {
			request.getRequestDispatcher("\\WEB-INF\\jsp\\admin\\updateCourse.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	// Reallocated
	protected void updateCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			DBManager dbm = new DBManager();
			Course course = new Course(Integer.valueOf(request.getParameter("id")), request.getParameter("subject"),
					request.getParameter("start"), request.getParameter("end"),
					Integer.valueOf(request.getParameter("teacherID")));
			dbm.updateCourse(course);
			dbm.closeCon();
			getCourses(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//Reallocated
	protected void getCourses(HttpServletRequest request, HttpServletResponse response) {
		DBManager dbm;
		try {
			dbm = new DBManager();
			List<Course> list = dbm.getCourses();
			request.setAttribute("courses", list);
			dbm.closeCon();
			request.getRequestDispatcher("\\WEB-INF\\jsp\\admin\\coursesList.jsp").forward(request, response);
		} catch (ClassNotFoundException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

//Reallocated
	protected void signUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			DBManager dbm = new DBManager();
			User user = dbm.registerUser(request.getParameter("status"), request.getParameter("login"),
					request.getParameter("password"));
			request.getSession().setAttribute("user", user);
			if (user.getStatus().equals("student")) {
				response.sendRedirect("student.jsp");
			} else {
				response.sendRedirect("index.jsp");
			}
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//Reallocated
	protected void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

//In process
	protected void getUsers(HttpServletRequest request, HttpServletResponse response) {
		try {
			DBManager dbm = new DBManager();
			List<User> list = dbm.getUsers();
			request.getSession().setAttribute("usersList", list);
			List<User> registers = dbm.getRequestsReg();
			request.getSession().setAttribute("requestsList", registers);
			dbm.closeCon();
			try {
				request.getRequestDispatcher("\\WEB-INF\\jsp\\admin\\usersList.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//Reallocated
	protected void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DBManager dbm = new DBManager();
			User user = dbm.getUserStatus(request.getParameter("login"), request.getParameter("password"));
			if (user == null) {
				request.setAttribute("errType", new String("User not found"));
				request.getRequestDispatcher("\\WEB-INF\\jsp\\error.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("user", user);
				if (user.getStatus().equals("waiting")) {
					response.sendRedirect("index.jsp");
				} else {
					response.sendRedirect(user.getStatus() + ".jsp");
				}
				dbm.closeCon();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//Reallocated
	protected void unblockStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			DBManager dbm = new DBManager();
			int id = Integer.valueOf(request.getParameter("id"));
			String status = request.getParameter("status");
			if (dbm.unblockStudent(id, status)) {
				getUsers(request, response);
			}
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//Reallocated
	protected void blockStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			DBManager dbm = new DBManager();
			int id = Integer.valueOf(request.getParameter("id"));
			String status = request.getParameter("status");
			if (dbm.blockStudent(id, status)) {
				getUsers(request, response);
			}
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//Reallocated
	protected void approve(HttpServletRequest request, HttpServletResponse response) {
		try {
			DBManager dbm = new DBManager();
			dbm.approveTeacher(request.getParameter("login"), request.getParameter("password"));
			dbm.closeCon();
			response.sendRedirect(request.getHeader("referer"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

//Not neccessary at the moment
	protected void signBox(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("\\WEB-INF\\jsp\\register.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return;
	}

	protected void addCustomer() {
		System.out.println("admin href");
	}
*/
}
