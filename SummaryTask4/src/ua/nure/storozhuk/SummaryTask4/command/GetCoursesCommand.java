package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

/**
 * Command for administrator panel to work with the list of the courses
 *
 */
public class GetCoursesCommand extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(GetCoursesCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null || !user.getStatus().equals("admin")) {
        	request.setAttribute("errType", "You are not allowed for this request");
        	return "WEB-INF\\jsp\\error.jsp";
        }
		LOG.debug("started");
		String forward = "";
		if (request.getParameter("incommand") == null) {
			LOG.debug("Incommand == null");
			forward = getCourses(request, response);
		} else {
			try {
				Method method = this.getClass().getDeclaredMethod(request.getParameter("incommand"),
						HttpServletRequest.class, HttpServletResponse.class);
				forward = (String) method.invoke(this, request, response);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			LOG.debug("finished");
			return forward;
		}
		LOG.debug("finished");
		return forward;
	}

	protected String createCourse(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if (!start.matches("[0-9]{4}-((1[0-2])|(0[1-9]))-(([1-2][0-9])|(0[1-9])|(3[0-1]))")
				|| !end.matches("[0-9]{4}-((1[0-2])|(0[1-9]))-(([1-2][0-9])|(0[1-9])|(3[0-1]))")) {
			LOG.trace("error on wrong date format enter");
			request.setAttribute("errType", "Error on wrong date format enter");
			return "WEB-INF\\jsp\\error.jsp";
		}
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(date2.compareTo(date1) < 0) {
			LOG.trace("Start date is later than end");
			request.setAttribute("errType", "Start date is later than end");
			return "WEB-INF\\jsp\\error.jsp";
		}
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			String subject = request.getParameter("subject");
			int teacherId = Integer.valueOf(request.getParameter("teacherID"));
			Course course = new Course(0, subject, start, end, teacherId, null);
			dbm.createCourse(course);
			forward = "controller?command=getCourses";
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

	protected String deleteCourse(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			dbm.deleteCourse(Integer.valueOf(request.getParameter("id")));
			dbm.closeCon();
			forward = getCourses(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

	/**
	 * Admin receives the page to update an existing course
	 * 
	 * @param request
	 * @param response
	 * @return forward link to the updateCourse page
	 */
	protected String getUpdateList(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		List<String> list = new LinkedList<String>();
		list.add(request.getParameter("courseId"));
		list.add(request.getParameter("courseSubject"));
		list.add(request.getParameter("courseStart"));
		list.add(request.getParameter("courseEnd"));
		list.add(request.getParameter("courseTeacher"));
		request.getSession().removeAttribute("course");
		Course course = new Course(Integer.valueOf(list.get(0)), list.get(1), list.get(2), list.get(3),
				Integer.valueOf(list.get(4)), null);
		request.setAttribute("course", course);
		forward = "\\WEB-INF\\jsp\\admin\\updateCourse.jsp";
		LOG.debug("finished");
		return forward;
	}

	/**
	 * From update page admin sends request to update course with values included to
	 * the request parameters
	 * 
	 * @param request
	 * @param response
	 * @return the forward page to the courseList if update has been done
	 *         successfully or to the error page of it hasn't
	 */
	protected String updateCourse(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		/**
		 * Checking the correct entered date format
		 */
		if (!start.matches("[0-9]{4}-((1[0-2])|(0[1-9]))-(([1-2][0-9])|(0[1-9])|(3[0-1]))")
				|| !end.matches("[0-9]{4}-((1[0-2])|(0[1-9]))-(([1-2][0-9])|(0[1-9])|(3[0-1]))")) {
			LOG.trace("error on wrong date format enter");
			request.setAttribute("errType", "Error on wrong date format enter");
			return "WEB-INF\\jsp\\error.jsp";
		}
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(date2.compareTo(date1) < 0) {
			LOG.trace("Start date is later than end");
			request.setAttribute("errType", "Start date is later than end");
			return "WEB-INF\\jsp\\error.jsp";
		}
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
		LOG.debug("finished");
		return forward;
	}

	protected String getCourses(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		DBManager dbm;
		String forward = "";
		try {
			dbm = new DBManager();
			List<Course> list = dbm.getCourses();
			HashSet<Integer> nums = new HashSet<>();
			for (Course course : list) {
				nums.add(course.getTeacherId());
			}
			request.setAttribute("courses", list);
			request.setAttribute("teachersID", nums);
			dbm.closeCon();
			forward = "\\WEB-INF\\jsp\\admin\\coursesList.jsp";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

}
