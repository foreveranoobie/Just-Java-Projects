package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;

public class GetCoursesCommand extends Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		if (request.getParameter("incommand") == null) {
			forward = getCourses(request, response);
		} else {
			try {
				Method method = this.getClass().getDeclaredMethod(request.getParameter("incommand"),
						HttpServletRequest.class, HttpServletResponse.class);
				forward = (String) method.invoke(this, request, response);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			return forward;
		}
		return forward;
	}
	
	protected String createCourse(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			String subject = request.getParameter("subject");
			int teacherId = Integer.valueOf(request.getParameter("teacherID"));
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			Course course = new Course(0, subject, start, end, teacherId, null);
			dbm.createCourse(course);
			forward = getCourses(request, response);
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return forward;
	}

	protected String deleteCourse(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			dbm.deleteCourse(Integer.valueOf(request.getParameter("id")));
			dbm.closeCon();
			forward = getCourses(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return forward;
	}

	protected String getUpdateList(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		String[] list = (String[]) request.getSession().getAttribute("course").toString().split(",\\s");
		request.getSession().removeAttribute("course");
		Course course = new Course(Integer.valueOf(list[0]), list[1], list[2], list[3], Integer.valueOf(list[4]), null);
		request.setAttribute("course", course);
		forward = "\\WEB-INF\\jsp\\admin\\updateCourse.jsp";
		//request.getRequestDispatcher("\\WEB-INF\\jsp\\admin\\updateCourse.jsp").forward(request, response);
		return forward;
	}

	protected String updateCourse(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			Course course = new Course(Integer.valueOf(request.getParameter("id")), request.getParameter("subject"),
					request.getParameter("start"), request.getParameter("end"),
					Integer.valueOf(request.getParameter("teacherID")), null);
			dbm.updateCourse(course);
			dbm.closeCon();
			response.sendRedirect("admin.jsp");
			forward = "redirect";
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

	protected String getCourses(HttpServletRequest request, HttpServletResponse response) {
		DBManager dbm;
		String forward = "";
		try {
			dbm = new DBManager();
			List<Course> list = dbm.getCourses();
			HashSet<Integer> nums = new HashSet<>();
			for(Course course : list) {
				nums.add(course.getTeacherId());
			}
			request.setAttribute("courses", list);
			request.setAttribute("teachersID", nums);
			dbm.closeCon();
			forward = "\\WEB-INF\\jsp\\admin\\coursesList.jsp";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
