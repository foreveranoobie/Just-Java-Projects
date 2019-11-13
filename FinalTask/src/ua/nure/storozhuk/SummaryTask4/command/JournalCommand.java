package ua.nure.storozhuk.SummaryTask4.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Journal;
import ua.nure.storozhuk.SummaryTask4.sql.entity.StudentsCourse;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

/**
 * Object to work with the teachers requests related to the journal
 *
 */
public class JournalCommand extends Command {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(JournalCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		if (request.getParameter("incommand") == null) {
			forward = getCurrentCourses(request, response);
		} else {
			try {
				Method method = this.getClass().getDeclaredMethod(request.getParameter("incommand"),
						HttpServletRequest.class, HttpServletResponse.class);
				forward = (String) method.invoke(this, request, response);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			return forward;
		}
		LOG.debug("finished");
		return forward;
	}
/**
 * Sorting courses which aren't finished
 * @param request
 * @param response
 */
	public void getSortedCourses(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		DBManager dbm;
		try {
			dbm = new DBManager();
			User user = (User) request.getSession().getAttribute("user");
			String subj = request.getParameter("subjectSort");
			int id = user.getId();
			List<StudentsCourse> list = new LinkedList<>();
			if (subj.equals("All")) {
				list = dbm.getTeacherCourses(id);
			} else {
				list = dbm.getTeacherCourses(id, subj);
			}
			request.setAttribute("journal", list);
			if (request.getParameter("order").isEmpty()) {
				Collections.sort(list, StudentsCourse.subjectsComparator());
				request.setAttribute("order", "asc");
			} else {
				Collections.sort(list, StudentsCourse.subjectsDescComparator());
				request.removeAttribute("order");
			}
			List<String> subjects = dbm.getCourses(id);
			List<Journal> journal = dbm.getJournal(id);
			request.setAttribute("journal", journal);
			request.setAttribute("courses", list);
			request.setAttribute("subjects", subjects);
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
	}
/**
 * Sorting the journal of finished courses
 * @param request
 * @param response
 */
	public void getSortedJournal(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		DBManager dbm;
		try {
			dbm = new DBManager();
			User user = (User) request.getSession().getAttribute("user");
			String subj = request.getParameter("subjectSort");
			int id = user.getId();
			List<Journal> list = new LinkedList<>();
			if (subj.equals("All")) {
				list = dbm.getJournal(id);
			} else {
				list = dbm.getJournal(id, subj);
			}
			request.setAttribute("journal", list);
			if (request.getParameter("order").isEmpty()) {
				Collections.sort(list, Journal.subjectComparator());
				request.setAttribute("order", "asc");
			} else {
				Collections.sort(list, Journal.subjectDescComparator());
				request.removeAttribute("order");
			}
			List<StudentsCourse> students = dbm.getTeacherCourses(id);
			List<String> subjects = dbm.getCourses(id);
			request.setAttribute("courses", students);
			request.setAttribute("subjects", subjects);
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
	}
/**
 * Receives the main information about courses and if sortCommand
 * is contained in request it calls the sorting functions next
 * @param request
 * @param response
 */
	public String getCurrentCourses(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		try {
			if (request.getParameter("toSort") == null) {
				DBManager dbm = new DBManager();
				User user = (User) request.getSession().getAttribute("user");
				int id = user.getId();
				List<Journal> list = dbm.getJournal(id);
				request.setAttribute("journal", list);
				List<StudentsCourse> students = dbm.getTeacherCourses(id);
				List<String> subjects = dbm.getCourses(id);
				request.setAttribute("courses", students);
				request.setAttribute("subjects", subjects);
				forward = "WEB-INF\\jsp\\teacher\\journal.jsp";
				dbm.closeCon();
			} else {
				try {
					Method method = this.getClass().getDeclaredMethod(request.getParameter("toSort"),
							HttpServletRequest.class, HttpServletResponse.class);
					method.invoke(this, request, response);
					forward = "WEB-INF\\jsp\\teacher\\journal.jsp";
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

	public String setMark(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		String[] nums = request.getSession().getAttribute("toMove").toString().split(",\\s");
		int courseId = Integer.valueOf(nums[0]);
		int studentId = Integer.valueOf(nums[1]);
		int mark = Integer.valueOf(request.getParameter("mark"));
		if(mark < 1 || mark > 5) {
			request.setAttribute("errType", "Wrong mark. Should be IN 1-5");
			return "WEB-INF\\jsp\\error.jsp";
		}
		try {
			DBManager dbm = new DBManager();
			dbm.setMark(courseId, studentId, mark);
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getSession().removeAttribute("toMove");
		forward = getCurrentCourses(request, response);
		LOG.debug("finished");
		return forward;
	}

}
