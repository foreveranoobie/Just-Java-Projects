package ua.nure.storozhuk.SummaryTask4.sql;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ua.nure.storozhuk.SummaryTask4.sql.entity.Course;

public class SortedBase extends DataBaseMain {
	public SortedBase() throws ClassNotFoundException {
		super();
	}

	public List<String> getDistinctUser(String param, int id) throws SQLException {
		List<String> list = new LinkedList<>();
		rs = st.executeQuery("select distinct " + param + " from users where id=" + id);
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		return list;
	}

	public List<String> getDistinctCourse(String param) throws SQLException {
		List<String> list = new LinkedList<>();
		rs = st.executeQuery("select distinct " + param + " from courses");
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		return list;
	}

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
				rs = st.executeQuery("SELECT id, subject, start, end, teacher_id, (SELECT login FROM users WHERE users.id = courses.teacher_id) "
						+ "FROM courses WHERE id IN "+diap.toString()+" ORDER BY(SELECT COUNT(*) " + "FROM student_courses "
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

//Get courses sorted by their own popularity
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

//Get courses sorted by subject alphabets
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

//Get courses sorted by term	
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
