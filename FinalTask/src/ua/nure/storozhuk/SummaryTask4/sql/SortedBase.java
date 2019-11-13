package ua.nure.storozhuk.SummaryTask4.sql;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;

/**
 * Class for special tables sorting with certain order or another certain
 * parameters using DataBase connection
 */
public class SortedBase extends DataBaseMain {
	public SortedBase() throws ClassNotFoundException {
		super();
	}

	/**
	 * Get distinct value from courses table
	 * 
	 * @param param the name of column header to get distinct values of
	 * @return String list of these distinct values
	 * @throws SQLException
	 */
	public List<String> getDistinctCourse(String param) throws SQLException {
		List<String> list = new LinkedList<>();
		rs = st.executeQuery("select distinct " + param + " from courses");
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		return list;
	}

	/**
	 * Get courses list of certain teacher
	 * 
	 * @param id of teacher
	 * @return Course list of certain teacher
	 */
	public List<Course> getCoursesOfTeacher(int id) {
		List<Course> list = new LinkedList<Course>();
		try {
			rs = st.executeQuery("select * from courses WHERE teacher_id=" + id);
			while (rs.next()) {
				list.add(new Course(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(), rs.getDate(4).toString(),
						rs.getInt(5), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Overloaded function. Added List of certain courses. Getting sorted order of
	 * courses by the count of students applied to them
	 * 
	 * @param order   asc (ascending) or desc (descending)
	 * @param courses the List of courses to count students of
	 * @return the List of sorted courses by their popularity
	 */
	public List<Course> getCoursesByPopularity(String order, List<Course> courses) {
		List<Course> list = new LinkedList<Course>();
		if (courses != null) {
			StringBuilder diap = new StringBuilder();
			diap.append("(");
			for (Course course : courses) {
				diap.append(course.getId() + ", ");
			}
			diap.delete(diap.length() - 2, diap.length());
			diap.append(")");
			try {
				rs = st.executeQuery(
						"SELECT id, subject, start, end, teacher_id, (SELECT login FROM users WHERE users.id = courses.teacher_id) "
								+ "FROM courses WHERE id IN " + diap.toString() + " ORDER BY(SELECT COUNT(*) "
								+ "FROM student_courses "
								+ "where courses.id = student_courses.course_id GROUP BY course_id) " + order);
				while (rs.next()) {
					list.add(new Course(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(),
							rs.getDate(4).toString(), rs.getInt(5), rs.getString(6)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * Getting the list of all courses sorted by the count of students applied for
	 * every course
	 * 
	 * @param order asc(ascending) or desc(descending)
	 * @return the List of courses sorted by popularity
	 */
	public List<Course> getCoursesByPopularity(String order) {
		List<Course> list = new LinkedList<Course>();
		try {
			rs = st.executeQuery("SELECT * FROM courses ORDER BY(SELECT COUNT(*) " + "FROM student_courses "
					+ "where courses.id = student_courses.course_id GROUP BY course_id) " + order);
			while (rs.next()) {
				list.add(new Course(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(), rs.getDate(4).toString(),
						rs.getInt(5), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Get courses sorted by subjects alphabetically
	 * 
	 * @param order asc(ascending) or desc(descending)
	 * @return List of sorted courses
	 */
	public List<Course> getCoursesBySubject(String order) {
		List<Course> list = new LinkedList<Course>();
		try {
			rs = st.executeQuery("select * from courses order by subject " + order);
			while (rs.next()) {
				list.add(new Course(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(), rs.getDate(4).toString(),
						rs.getInt(5), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Sorting courses by the longest term of studying (End date - Start date)
	 * 
	 * @param order asc(ascending) or desc(descending)
	 * @return List of courses sorted by studying term
	 */
	public List<Course> getCoursesByLong(String order) {
		List<Course> list = new LinkedList<Course>();
		try {
			rs = st.executeQuery("select * from courses order by (end - start) " + order);
			while (rs.next()) {
				list.add(new Course(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(), rs.getDate(4).toString(),
						rs.getInt(5), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void closeCon() {
		super.closeCon();
	}
}
