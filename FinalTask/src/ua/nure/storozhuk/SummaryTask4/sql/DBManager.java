package ua.nure.storozhuk.SummaryTask4.sql;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.nure.storozhuk.SummaryTask4.sql.entity.*;

/**
 * @author Alex
 *
 */
public class DBManager extends DataBaseMain{
	
	public DBManager() throws ClassNotFoundException {
		super();
	}
//Applies the student for a course
	public void applyStudent(String subject, String teacher, int studentId) {
		try {
			rs = st.executeQuery("select id from users where login='" + teacher + "'");
			if (rs.next()) {
				int teacherId = rs.getInt(1);
				st.executeUpdate("insert into student_courses (student_id, course_id)"
						+ " VALUES("+studentId+", (SELECT id FROM courses "
								+ "WHERE subject='"+subject+"' and teacher_id="+teacherId+"))");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//Student receives the courses he hasn't applied for yet
	public List<Course> getUnappliedCourses(int studentId) {
		List<Course> list = new LinkedList<>();
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		try {
			rs = st.executeQuery(
					"SELECT subject, start, end, (SELECT users.login from users WHERE users.id = teacher_id) AS teacher,"
					+ "id, teacher_id "
							+ "FROM final_schema.courses "
							+ "WHERE id NOT IN (SELECT course_id FROM final_schema.student_courses WHERE student_id ="
							+ studentId + ") " + "AND start > '" + date+"'");
			while (rs.next()) {
				list.add(new Course(rs.getInt(5), rs.getString(1), rs.getDate(2).toString(), rs.getDate(3).toString(), rs.getInt(6), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//Getting the courses of student which are started and in progress at the moment
	public List<Course> getProcessingCourses(int studentId) {
		List<Course> list = new LinkedList<>();
		try {
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			rs = st.executeQuery(
					"select c.subject, c.start, c.end, (SELECT users.login from users WHERE users.id = c.teacher_id) "
							+ "AS teacher, c.id, c.teacher_id "
							+ "FROM courses c JOIN student_courses sc ON c.id = sc.course_id WHERE sc.student_id = "
							+ studentId + " AND c.start <= '" + date + "' AND c.end > '" + date + "'");
			while (rs.next()) {
				list.add(new Course(rs.getInt(5), rs.getString(1), rs.getDate(2).toString(), rs.getDate(3).toString(), rs.getInt(6), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//List of courses which aren't started yet
	public List<Course> getPreparingCourses(int studentId) {
		List<Course> list = new LinkedList<>();
		try {
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			rs = st.executeQuery(
					"select c.subject, c.start, c.end, (SELECT users.login from users WHERE users.id = c.teacher_id) "
							+ "AS teacher, c.id, c.teacher_id "
							+ "FROM courses c JOIN student_courses sc ON c.id = sc.course_id WHERE sc.student_id = "
							+ studentId + " AND c.start > '" + date + "'");
			while (rs.next()) {
				list.add(new Course(rs.getInt(5), rs.getString(1), rs.getDate(2).toString(), rs.getDate(3).toString(), rs.getInt(6), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//List of courses which aren't started yet
		public List<Course> getPreparingCourses(int studentId, String subject) {
			List<Course> list = new LinkedList<>();
			try {
				long millis = System.currentTimeMillis();
				Date date = new Date(millis);
				rs = st.executeQuery(
						"select c.subject, c.start, c.end, (SELECT users.login from users WHERE users.id = c.teacher_id) "
								+ "AS teacher, c.id, c.teacher_id "
								+ "FROM courses c JOIN student_courses sc ON c.id = sc.course_id WHERE sc.student_id = "
								+ studentId + " AND c.start > '" + date + "' AND c.subject='"+subject+"'");
				while (rs.next()) {
					list.add(new Course(rs.getInt(5), rs.getString(1), rs.getDate(2).toString(), rs.getDate(3).toString(), rs.getInt(6), rs.getString(4)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		//Student read the result of finished courses by himself with marks
		public List<Journal>getFinishedJournal(int studentId, int mark) {
	        List<Journal> coursesResult = new LinkedList<>();
			try {
				rs = st.executeQuery("SELECT courses.subject, journal.mark FROM courses JOIN "
						+ "journal ON courses.id = journal.course_id WHERE journal.student_id =" + studentId+
						" AND journal.mark='"+mark+"'");
				while (rs.next()) {
					coursesResult.add(new Journal(0, 0, rs.getInt(2), rs.getString(1)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return coursesResult;
		}
		
		//Student read the result of finished courses by himself with marks
		public List<Journal>getFinishedJournal(int studentId, String subject) {
	        List<Journal> coursesResult = new LinkedList<>();
			try {
				rs = st.executeQuery("SELECT courses.subject, journal.mark FROM courses JOIN "
						+ "journal ON courses.id = journal.course_id WHERE journal.student_id =" + studentId+
						" AND courses.subject='"+subject+"'");
				while (rs.next()) {
					coursesResult.add(new Journal(0, 0, rs.getInt(2), rs.getString(1)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return coursesResult;
		}

//Student read the result of finished courses by himself with marks
	public List<Journal>getFinishedJournal(int studentId) {
        List<Journal> coursesResult = new LinkedList<>();
		try {
			rs = st.executeQuery("SELECT courses.subject, journal.mark FROM courses JOIN "
					+ "journal ON courses.id = journal.course_id WHERE journal.student_id =" + studentId);
			while (rs.next()) {
				coursesResult.add(new Journal(0, 0, rs.getInt(2), rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursesResult;
	}

//Sets a mark in journal of finished course and deletes the course from list of students courses
	public void setMark(int courseId, int studentId, int mark) {
		try {
			st.executeUpdate("INSERT INTO journal (course_id, student_id, mark) VALUES (" + courseId + ", " + studentId
					+ ", " + mark + ")");
			st.executeUpdate(
					"DELETE FROM student_courses WHERE course_id=" + courseId + " AND student_id=" + studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<StudentsCourse> getTeacherCourses(int teacherId, String subject) {
		List<StudentsCourse> list = new LinkedList<>();
		try {
			rs = st.executeQuery("SELECT sc.course_id, sc.student_id, courses.end, courses.subject FROM final_schema.student_courses sc "
					+ "JOIN final_schema.courses ON sc.course_id = courses.id WHERE courses.teacher_id =" + teacherId+" "
							+ "AND courses.subject='"+subject+"'");
			Date date;
			long millis = System.currentTimeMillis();
			Date currentdate = new Date(millis);
			while (rs.next()) {
				date = rs.getDate(3);
				if (date.compareTo(currentdate) > 0) {
					list.add(new StudentsCourse(rs.getInt(1), rs.getInt(2), "processing", rs.getString(4)));
				} else {
					list.add(new StudentsCourse(rs.getInt(1), rs.getInt(2), "finished", rs.getString(4)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<StudentsCourse> getTeacherCourses(int teacherId) {
		List<StudentsCourse> list = new LinkedList<>();
		try {
			rs = st.executeQuery("SELECT sc.course_id, sc.student_id, courses.end, courses.subject FROM final_schema.student_courses sc "
					+ "JOIN final_schema.courses ON sc.course_id = courses.id WHERE courses.teacher_id =" + teacherId);
			Date date;
			long millis = System.currentTimeMillis();
			Date currentdate = new Date(millis);
			while (rs.next()) {
				date = rs.getDate(3);
				if (date.compareTo(currentdate) > 0) {
					list.add(new StudentsCourse(rs.getInt(1), rs.getInt(2), "processing", rs.getString(4)));
				} else {
					list.add(new StudentsCourse(rs.getInt(1), rs.getInt(2), "finished", rs.getString(4)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Journal> getJournal(int id, String subject) {
		LinkedList<Journal> list = new LinkedList<>();
		try {
			/*rs = st.executeQuery("SELECT course_id, student_id, mark, (select subject from courses " + 
					"where courses.id = journal.course_id) AS subject FROM journal WHERE course_id IN (SELECT id FROM "
					+ "courses WHERE teacher_id=" + id + ") AND subject='"+subject+"'");*/
			rs = st.executeQuery("SELECT course_id, student_id, mark, (select courses.subject from courses where courses.id = j.course_id)" + 
					" AS subject FROM journal j JOIN final_schema.courses sc ON j.course_id = sc.id" + 
					" WHERE j.course_id IN (SELECT id FROM courses WHERE teacher_id="+id+") " + 
					" AND sc.subject='"+subject+"'");
			while (rs.next()) {
				list.add(new Journal(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Journal> getJournal(int id) {
		LinkedList<Journal> list = new LinkedList<>();
		try {
			rs = st.executeQuery("SELECT course_id, student_id, mark, (select subject from courses " + 
					"where courses.id = journal.course_id) AS subject FROM journal WHERE course_id IN (SELECT id FROM "
					+ "courses WHERE teacher_id=" + id + ")");
			while (rs.next()) {
				list.add(new Journal(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void createCourse(Course course) {
		try {
			st.executeUpdate("INSERT INTO courses (subject, start, end, teacher_id) VALUES ('" + course.getSubject()
					+ "', '" + course.getStart() + "', '" + course.getEnd() + "', " + course.getTeacherId() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCourse(Course course) {
		try {
			st.executeUpdate("UPDATE courses SET subject='" + course.getSubject() + "', start='" + course.getStart()
					+ "', end='" + course.getEnd() + "', teacher_id=" + course.getTeacherId() + " where id="
					+ course.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCourse(int id) {
		try {
			st.executeUpdate("DELETE FROM courses WHERE id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getCourses(int teacherID) {
		List<String> list = new LinkedList<String>();
		try {
			rs = st.executeQuery("select distinct subject from courses where teacher_id="+teacherID);
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Course> getCourses() {
		List<Course> list = new LinkedList<Course>();
		try {
			rs = st.executeQuery("select * from courses");
			while (rs.next()) {
				list.add(new Course(rs.getInt(1), rs.getString(2), rs.getDate(3).toString(), rs.getDate(4).toString(),
						rs.getInt(5), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean unblockStudent(int id, String status) {
		if (!"studentBlocked".equals(status)) {
			return false;
		} else {
			try {
				st.executeUpdate("DELETE FROM blocked WHERE id=" + id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	public boolean blockStudent(int id, String status) {
		if (!"student".equals(status)) {
			return false;
		} else {
			try {
				st.executeUpdate("INSERT INTO blocked (id) VALUES (" + id + ")");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	public User registerUser(String status, String login, String password) {
		if (login.isEmpty() || password.isEmpty()) {
			return null;
		}
		User user = null;
		switch (status) {
		case "student":
			try {
				st.executeUpdate("INSERT INTO users (login, password, role) " + "VALUES ('" + login + "', '" + password
						+ "', (SELECT roles.id FROM roles WHERE roles.role_name = 'student'))");
				rs = st.executeQuery("SELECT * FROM users ORDER BY users.id ASC");
				if (rs.last()) {
					user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), status);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "teacher":
			try {
				st.executeUpdate("INSERT INTO register_request (username, password)" + "VALUES ('" + login + "', '"
						+ password + "')");
				user = new User(0, login, password, "waiting");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return user;
	}

	public User getUserStatus(String login, String password) {
		User user = null;
		try {
			rs = st.executeQuery(
					"SELECT users.id, users.login, users.password, roles.role_name FROM users JOIN roles ON users.role = roles.id WHERE users.login = '"
							+ login + "' AND users.password = '" + password + "'");
			if (rs.next()) {
				int id = rs.getInt(1);
				String status = rs.getString(4);
				if(status.equals("student")) {
					ResultSet studCheck = st.executeQuery("SELECT id from blocked where id="+id);
					if(studCheck.next()) {
						user = new User(id, login, password, "studentBlocked");
						return user;
					}
				}
				user = new User(id, login, password, status);
			} else {
				rs = st.executeQuery("SELECT username, password FROM register_request WHERE" + " username='" + login
						+ "' AND password='" + password + "'");
				if (rs.next()) {
					user = new User(0, login, password, "waiting");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getUsers() {
		LinkedList<User> list = new LinkedList<>();
		try {
			rs = st.executeQuery("SELECT id, login, password, (SELECT roles.role_name FROM roles "
					+ "WHERE users.role = roles.id) AS status FROM users ORDER BY users.role ASC");
			Statement studentState = conn.createStatement();
			while (rs.next()) {
				if (rs.getString(4).equals("student")) {
					ResultSet studentSet = studentState
							.executeQuery("SELECT id FROM blocked WHERE blocked.id = " + rs.getInt(1));
					if (studentSet.next()) {
						list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), "studentBlocked"));
						studentSet.close();
					} else {
						list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), "student"));
					}
				} else {
					list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
//Getting list of teacher's requests to be registered
	public List<User> getRequestsReg() {
		List<User> list = new LinkedList<>();
		try {
			rs = st.executeQuery("SELECT * FROM register_request");
			while (rs.next()) {
				list.add(new User(0, rs.getString(1), rs.getString(2), "waiting"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
//Approve teacher's registration request
	public boolean approveTeacher(String login, String password) {
		try {
			st.executeUpdate(
					"INSERT INTO users (login, password, role) VALUES ('" + login + "', '" + password + "', '2')");
			st.executeUpdate(
					"DELETE FROM register_request WHERE username='" + login + "' AND password='" + password + "'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
