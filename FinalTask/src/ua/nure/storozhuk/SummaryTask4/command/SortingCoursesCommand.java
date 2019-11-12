package ua.nure.storozhuk.SummaryTask4.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.storozhuk.SummaryTask4.sql.DBManager;
import ua.nure.storozhuk.SummaryTask4.sql.SortedBase;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;
import ua.nure.storozhuk.SummaryTask4.sql.entity.Journal;
import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

public class SortingCoursesCommand extends Command {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		if (request.getParameter("incommand") == null) {
			// forward = getCourses(request, response);
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
		return forward;
	}

	public String sortStudentCourses(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		List<Course> list = (List<Course>) request.getAttribute("list");
		if (request.getParameter("order") == null || request.getParameter("order").isEmpty()) {
			Collections.sort(list, Course.subjectComparator());
			request.setAttribute("order", "desc");
		} else {
			Collections.sort(list, Course.subjectDescComparator());
			request.removeAttribute("order");
		}
		request.setAttribute("list", list);
		return forward;
	}

	public String sortPopularCourses(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		List<Course> list = (List<Course>) request.getSession().getAttribute("courses");
		String distCourses = request.getParameter("distCourses");
		distCourses = distCourses.replace("[", "");
		distCourses = distCourses.replace("]", "");
		String[] array = distCourses.split(",\\s");
		List<String> distCourse = new LinkedList<String>();
		Collections.addAll(distCourse, array);
		request.getSession().removeAttribute("courses");
		if (list != null && list.size() != 0) {
			try {
				SortedBase sBase = new SortedBase();
				if (request.getParameter("order") == null || request.getParameter("order").isEmpty()) {
					list = sBase.getCoursesByPopularity("asc", list);
					request.setAttribute("order", "desc");
				} else {
					list = sBase.getCoursesByPopularity("desc", list);
					request.removeAttribute("order");
				}
				sBase.closeCon();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("distCourse", distCourse);
		request.setAttribute("list", list);
		forward = "WEB-INF\\jsp\\student\\preparingCourses.jsp";
		return forward;
	}

	public String sortCoursesLong(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		List<Course> list = (List<Course>) request.getSession().getAttribute("courses");
		String distCourses = request.getParameter("distCourses");
		distCourses = distCourses.replace("[", "");
		distCourses = distCourses.replace("]", "");
		String[] array = distCourses.split(",\\s");
		List<String> distCourse = new LinkedList<String>();
		Collections.addAll(distCourse, array);
		request.getSession().removeAttribute("courses");
		if (list.size() != 0) {
			if (request.getParameter("order") == null || request.getParameter("order").isEmpty()) {
				Collections.sort(list, Course.subjectComparator());
				request.setAttribute("order", "desc");
			} else {
				Collections.sort(list, Course.subjectDescComparator());
				request.removeAttribute("order");
			}
		}
		request.setAttribute("list", list);
		request.setAttribute("distCourse", distCourse);
		forward = "WEB-INF\\jsp\\student\\preparingCourses.jsp";
		return forward;
	}

	public String getTeacherSubject(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		List<Course> list = new LinkedList<Course>();
		if (request.getParameter("teacherNum").equals("All")) {
			return forward;
		}
		int lookId = Integer.valueOf(request.getParameter("teacherNum"));
		for (Course course : (List<Course>) request.getAttribute("list")) {
			if (course.getTeacherId() == lookId) {
				list.add(course);
			}
		}
		request.setAttribute("list", list);
		return forward;
	}

	public String sortStudentTeachers(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		User user = (User) request.getAttribute("user");
		int teacherId = Integer.valueOf(request.getParameter("teacherID"));
		DBManager dbm;
		try {
			dbm = new DBManager();
			List<Course> list = dbm.getProcessingCourses(user.getId());
			List<Course> result = new LinkedList<Course>();
			for (Course course : list) {
				if (course.getTeacherId() == teacherId) {
					result.add(course);
				}
			}
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return forward;
	}

//Need refactoring	
	public String sortStudentSubjects(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException {
		String forward = "";
		List<Course> list;
		User user = (User) request.getSession().getAttribute("user");
		if (request.getParameter("subjectName").equals("All")) {
			DBManager dbm = new DBManager();
			list = dbm.getPreparingCourses(user.getId());
			if (request.getParameter("order").isEmpty()) {
				Collections.sort(list, Course.subjectComparator());
				request.setAttribute("order", "desc");
			} else {
				Collections.sort(list, Course.subjectDescComparator());
				request.removeAttribute("order");
			}
			dbm.closeCon();
		} else {
			DBManager dbm = new DBManager();
			list = dbm.getPreparingCourses(user.getId(), request.getParameter("subjectName"));
			if (request.getParameter("order").isEmpty()) {
				Collections.sort(list, Course.subjectComparator());
				request.setAttribute("order", "desc");
			} else {
				Collections.sort(list, Course.subjectDescComparator());
				request.removeAttribute("order");
			}
			dbm.closeCon();
		}
		String distCourses = request.getParameter("distCourses");
		distCourses = distCourses.replace("[", "");
		distCourses = distCourses.replace("]", "");
		String[] array = distCourses.split(",\\s");
		List<String> distCourse = new LinkedList<String>();
		Collections.addAll(distCourse, array);
		request.getSession().removeAttribute("subjects");
		request.setAttribute("list", list);
		request.setAttribute("distCourse", distCourse);
		forward = "WEB-INF\\jsp\\student\\preparingCourses.jsp";
		return forward;
	}

	public String sortPopulars(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		List<Course> list = new LinkedList<Course>();
		HashSet<Integer> nums = new HashSet<>();
		try {
			SortedBase dbSorting = new SortedBase();
			if (request.getParameter("order").isEmpty()) {
				list = dbSorting.getCoursesByPopularity("asc");
				request.setAttribute("order", "desc");
			} else {
				list = dbSorting.getCoursesByPopularity("desc");
			}
			for (Course course : list) {
				nums.add(course.getTeacherId());
			}
			request.setAttribute("courses", list);
			request.setAttribute("teachersID", nums);
			dbSorting.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		forward = "\\WEB-INF\\jsp\\admin\\coursesList.jsp";
		return forward;
	}

	public String teacherOrder(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		List<Course> list = null;
		String id = request.getParameter("teacherNum");
		HashSet<Integer> nums = new HashSet<>();
		try {
			SortedBase dbSorting = new SortedBase();
			if (id.equals("All")) {
				DBManager dbm = new DBManager();
				list = dbm.getCourses();
				dbm.closeCon();
			} else {
				list = dbSorting.getCoursesOfTeacher(Integer.valueOf(id));
			}
			for (Course course : list) {
				nums.add(course.getTeacherId());
			}
			request.setAttribute("teachersID", nums);
			request.setAttribute("courses", list);
			forward = "\\WEB-INF\\jsp\\admin\\coursesList.jsp";
			dbSorting.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return forward;
	}

	public String sortByTerms(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		SortedBase dbSorting = null;
		HashSet<Integer> nums = new HashSet<>();
		try {
			dbSorting = new SortedBase();
			List<Course> list = null;
			if (request.getParameter("order").isEmpty()) {
				list = dbSorting.getCoursesByLong("asc");
				request.setAttribute("order", "desc");
			} else {
				list = dbSorting.getCoursesByLong("desc");
				request.removeAttribute("order");
			}
			for (Course course : list) {
				nums.add(course.getTeacherId());
			}
			dbSorting.closeCon();
			request.setAttribute("courses", list);
			request.setAttribute("teachersID", nums);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		forward = "\\WEB-INF\\jsp\\admin\\coursesList.jsp";
		return forward;
	}

//Sorting courses for administrator
	public String sortAdminCourses(HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		List<Course> list = (List<Course>) request.getSession().getAttribute("list");
		HashSet<Integer> nums = new HashSet<>();
		if (request.getParameter("order").isEmpty()) {
			Collections.sort(list, Course.subjectComparator());
			request.setAttribute("order", "desc");
		} else if ("desc".equals(request.getParameter("order"))) {
			Collections.sort(list, Course.subjectDescComparator());
			request.removeAttribute("order");
		}
		for (Course course : list) {
			nums.add(course.getTeacherId());
		}
		request.getSession().removeAttribute("list");
		request.setAttribute("courses", list);
		request.setAttribute("teachersID", nums);
		forward = "\\WEB-INF\\jsp\\admin\\coursesList.jsp";
		return forward;
	}

	public void sortedFinishedCourses(HttpServletRequest request, HttpServletResponse response) {
		DBManager dbm;
		try {
			String subject = request.getParameter("subjSelect");
			dbm = new DBManager();
			User user = (User) request.getSession().getAttribute("user");
			List<Journal> coursesResult = null;
			if (subject.equals("All")) {
				coursesResult = dbm.getFinishedJournal(user.getId());
			} else {
				coursesResult = dbm.getFinishedJournal(user.getId(), subject);
			}
			List<Integer> marks = new LinkedList<>();
			Set<String> subjects = new HashSet<String>();
			for (Journal journal : coursesResult) {
				marks.add(journal.getMark());
				subjects.add(journal.getSubject());
			}
			if (request.getParameter("order").isEmpty()) {
				Collections.sort(coursesResult, Journal.subjectComparator());
				request.setAttribute("order", "asc");
			} else {
				Collections.sort(coursesResult, Journal.subjectDescComparator());
				request.removeAttribute("order");
			}
			request.setAttribute("journal", coursesResult);
			request.setAttribute("marks", marks);
			request.setAttribute("subjects", subjects);
			dbm.closeCon();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
