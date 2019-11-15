package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.SortedBase;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Journal;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

/**
 * Command for processing student's requests about certain courses reading
 *
 */
public class StudentCoursesCommand extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(StudentCoursesCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		try {
			Method method = this.getClass().getDeclaredMethod(request.getParameter("Coursecommand"),
					HttpServletRequest.class, HttpServletResponse.class);
			forward = (String) method.invoke(this, request, response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

	/**
	 * Applies student for the choosen course
	 * 
	 * @param request
	 * @param response
	 */
	public String applyStudent(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		User user = (User) request.getSession().getAttribute("user");
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			if (!dbm.checkBlocked(user.getId())) {
				String[] info = request.getSession().getAttribute("toMove").toString().split(",\\s");
				dbm.applyStudent(info[0], info[1], user.getId());
				request.getSession().removeAttribute("toMove");
				forward = "redirect";
				try {
					response.sendRedirect("index.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
				dbm.closeCon();
			} else {
				dbm.closeCon();
				request.setAttribute("errType", "You are blocked and can't apply for a course");
				LOG.trace("Blocked student tried to apply for a course. ID =" + user.getId());
				return "WEB-INF\\jsp\\error.jsp";
			}
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

	/**
	 * The list of courses studen't hasn't applied for yet and hasn't started
	 * 
	 * @param request
	 * @param response
	 */
	public String otherCourses(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			SortedBase sBase = new SortedBase();
			User user = (User) request.getSession().getAttribute("user");
			List<String> distCourse = sBase.getDistinctCourse("subject");
			List<Course> list = dbm.getUnappliedCourses(user.getId());
			List<Course> ordered = new LinkedList<Course>();
			HashSet<Integer> teacherID = new HashSet<>();
			if (list.size() != 0) {
				if (request.getParameter("subjectName") != null && !request.getParameter("subjectName").equals("All")) {
					for (Course course : list) {
						if (course.getSubject().equals(request.getParameter("subjectName"))) {
							ordered.add(course);
						}
					}
					request.setAttribute("list", ordered);
				} else {
					request.setAttribute("list", list);
				}
				for (Course course : list) {
					teacherID.add(course.getTeacherId());
				}
				if (request.getParameter("incommand") != null) {
					SortingCoursesCommand scc = new SortingCoursesCommand();
					scc.execute(request, response);
				}
			}
			request.setAttribute("distCourse", distCourse);
			request.setAttribute("teacherID", teacherID);
			forward = "WEB-INF\\jsp\\student\\otherCourses.jsp";
			dbm.closeCon();
			sBase.closeCon();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

	/**
	 * Courses applied by student and in progress now
	 * 
	 * @param request
	 * @param response
	 */
	public String progressCourses(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			SortedBase sBase = new SortedBase();
			User user = (User) request.getSession().getAttribute("user");
			List<String> distCourse = sBase.getDistinctCourse("subject");
			List<Course> list = dbm.getProcessingCourses(user.getId());
			List<Course> ordered = new LinkedList<Course>();
			HashSet<Integer> teacherID = new HashSet<>();
			if (list.size() != 0) {
				if (request.getParameter("subjectName") != null && !request.getParameter("subjectName").equals("All")) {
					for (Course course : list) {
						if (course.getSubject().equals(request.getParameter("subjectName"))) {
							ordered.add(course);
						}
					}
					request.setAttribute("list", ordered);
				} else {
					request.setAttribute("list", list);
				}
				for (Course course : list) {
					teacherID.add(course.getTeacherId());
				}
				if (request.getParameter("incommand") != null) {
					SortingCoursesCommand scc = new SortingCoursesCommand();
					scc.execute(request, response);
				}
			}
			request.setAttribute("distCourse", distCourse);
			request.setAttribute("teacherID", teacherID);
			forward = "WEB-INF\\jsp\\student\\progressCourses.jsp";
			dbm.closeCon();
			sBase.closeCon();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

	/**
	 * List of applied by student courses but hasn't started yet
	 * 
	 * @param request
	 * @param response
	 */
	public String preparingCourses(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		try {
			DBManager dbm = new DBManager();
			SortedBase sBase = new SortedBase();
			User user = (User) request.getSession().getAttribute("user");
			List<Course> list = dbm.getPreparingCourses(user.getId());
			List<String> distCourse = sBase.getDistinctCourse("subject");
			request.setAttribute("list", list);
			request.setAttribute("distCourse", distCourse);
			if (request.getParameter("incommand") != null) {
				SortingCoursesCommand scc = new SortingCoursesCommand();
				scc.execute(request, response);
			}
			forward = "WEB-INF\\jsp\\student\\preparingCourses.jsp";
			dbm.closeCon();
			sBase.closeCon();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

	/**
	 * Get the list of finished courses by student with mark and subject name
	 * 
	 * @param request
	 * @param response
	 */
	public String finishedCourses(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		if (request.getParameter("incommand") == null) {
			DBManager dbm;
			try {
				dbm = new DBManager();
				User user = (User) request.getSession().getAttribute("user");
				List<Journal> coursesResult = dbm.getFinishedJournal(user.getId());
				List<Integer> marks = new LinkedList<>();
				Set<String> subjects = new HashSet<String>();
				for (Journal journal : coursesResult) {
					marks.add(journal.getMark());
					subjects.add(journal.getSubject());
				}
				Collections.sort(marks);
				request.setAttribute("journal", coursesResult);
				request.setAttribute("marks", marks);
				request.setAttribute("subjects", subjects);
				forward = "WEB-INF\\jsp\\student\\finishedCourses.jsp";
				dbm.closeCon();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			SortingCoursesCommand scc = new SortingCoursesCommand();
			scc.execute(request, response);
			forward = "WEB-INF\\jsp\\student\\finishedCourses.jsp";
		}
		LOG.debug("finished");
		return forward;
	}
}
