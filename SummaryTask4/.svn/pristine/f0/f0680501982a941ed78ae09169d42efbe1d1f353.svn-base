package ua.nure.storozhuk.SummaryTask4.sql;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.storozhuk.SummaryTask4.sql.entity.*;

/**
 * DBManager is inherited from DataBaseMain. It contains of main functions
 * applicable to database. Connection opening and closing created in parent
 * class
 */
public class DBManager extends DataBaseMain {
	private static final Logger LOG = Logger.getLogger(DBManager.class);

	public DBManager() throws ClassNotFoundException {
		super();
	}

	/**
	 * Function applies student for a course.
	 * 
	 * @param subject   the name of the course subject
	 * @param teacher   login of teacher who leads the course
	 * @param studentId student to be applied
	 */
	public void applyStudent(String subject, String teacher, int studentId) {
		try {
			rs = st.executeQuery("select id from users where login='" + teacher + "'");
			if (rs.next()) {
				int teacherId = rs.getInt(1);
				st.executeUpdate("insert into student_courses (student_id, course_id)" + " VALUES(" + studentId
						+ ", (SELECT id FROM courses " + "WHERE subject='" + subject + "' and teacher_id=" + teacherId
						+ "))");
				LOG.trace(" Student with ID: " + studentId + " was applied to a subject: " + subject + " of teacher: "
						+ teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Student receives the list of preparing courses Where he isn't applied for
	 * 
	 * @param studentId Student ID
	 * @return {@code List<Course>} of courses
	 */
	public List<Course> getUnappliedCourses(int studentId) {
		List<Course> list = new LinkedList<>();
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		try {
			rs = st.executeQuery(
					"SELECT subject, start, end, (SELECT users.login from users WHERE users.id = teacher_id) AS teacher,"
							+ "id, teacher_id " + "FROM courses "
							+ "WHERE id NOT IN (SELECT course_id FROM student_courses WHERE student_id =" + studentId
							+ ") " + "AND start > '" + date + "'");
			while (rs.next()) {
				list.add(new Course(rs.getInt(5), rs.getString(1), rs.getDate(2).toString(), rs.getDate(3).toString(),
						rs.getInt(6), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Student receives the list of courses which are applied by him and has begun
	 * but hasn't finished yed
	 * 
	 * @param studentId Student ID
	 * @return {@code List<Course>} of courses
	 */
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
				list.add(new Course(rs.getInt(5), rs.getString(1), rs.getDate(2).toString(), rs.getDate(3).toString(),
						rs.getInt(6), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Student receives the list of courses which are applied by him and hasn't
	 * begun yet
	 * 
	 * @param studentId Student ID
	 * @return {@code List<Course>} of courses
	 */
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
				list.add(new Course(rs.getInt(5), rs.getString(1), rs.getDate(2).toString(), rs.getDate(3).toString(),
						rs.getInt(6), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Overloaded function to get preparing course by subject name chosen by student
	 * in table
	 * 
	 * @param studentId Student ID
	 * @param subject   The subject name
	 * @return {@code List<Course>} of courses
	 */
	public List<Course> getPreparingCourses(int studentId, String subject) {
		List<Course> list = new LinkedList<>();
		try {
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			rs = st.executeQuery(
					"select c.subject, c.start, c.end, (SELECT users.login from users WHERE users.id = c.teacher_id) "
							+ "AS teacher, c.id, c.teacher_id "
							+ "FROM courses c JOIN student_courses sc ON c.id = sc.course_id WHERE sc.student_id = "
							+ studentId + " AND c.start > '" + date + "' AND c.subject='" + subject + "'");
			while (rs.next()) {
				list.add(new Course(rs.getInt(5), rs.getString(1), rs.getDate(2).toString(), rs.getDate(3).toString(),
						rs.getInt(6), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Overloaded function to get the mark of chosen subject
	 * 
	 * @param studentId studentId
	 * @param subject   subject name
	 * @return LinkedList of the course with mark
	 */
	public List<Journal> getFinishedJournal(int studentId, String subject) {
		List<Journal> coursesResult = new LinkedList<>();
		try {
			rs = st.executeQuery("SELECT courses.subject, journal.mark FROM courses JOIN "
					+ "journal ON courses.id = journal.course_id WHERE journal.student_id =" + studentId
					+ " AND courses.subject='" + subject + "'");
			while (rs.next()) {
				coursesResult.add(new Journal(0, 0, rs.getInt(2), rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coursesResult;
	}

	/**
	 * Student receives the list of courses he has finished with marks setted by
	 * teacher
	 * 
	 * @param studentId student ID
	 * @return LinkedList of all finished courses applied by student
	 */
	public List<Journal> getFinishedJournal(int studentId) {
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

	/**
	 * Teacher sets mark for a student after course ending
	 * 
	 * @param courseId  ID of the course
	 * @param studentId ID of the student
	 * @param mark      The mark to set
	 */
	public void setMark(int courseId, int studentId, int mark) {
		try {
			st.executeUpdate("INSERT INTO journal (course_id, student_id, mark) VALUES (" + courseId + ", " + studentId
					+ ", " + mark + ")");
			LOG.trace("Student " + studentId + " got mark " + mark + " for course(id) " + courseId);
			st.executeUpdate(
					"DELETE FROM student_courses WHERE course_id=" + courseId + " AND student_id=" + studentId);
			LOG.trace("Student " + studentId + " has been deleted from course(id) " + courseId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Overloaded function of getting teacher's leading courses
	 * 
	 * @param teacherId ID of teacher
	 * @param subject   Name of course
	 * @return LinkedList of courses
	 */
	public List<StudentsCourse> getTeacherCourses(int teacherId, String subject) {
		List<StudentsCourse> list = new LinkedList<>();
		try {
			rs = st.executeQuery(
					"SELECT sc.course_id, sc.student_id, courses.end, courses.subject FROM student_courses sc "
							+ "JOIN courses ON sc.course_id = courses.id WHERE courses.teacher_id =" + teacherId + " "
							+ "AND courses.subject='" + subject + "'");
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

	/**
	 * Teacher receives the list of courses he is leading at
	 * 
	 * @param teacherId ID of the teacher
	 * @return LinkedList of courses
	 */
	public List<StudentsCourse> getTeacherCourses(int teacherId) {
		List<StudentsCourse> list = new LinkedList<>();
		try {
			rs = st.executeQuery(
					"SELECT sc.course_id, sc.student_id, courses.end, courses.subject FROM student_courses sc "
							+ "JOIN courses ON sc.course_id = courses.id WHERE courses.teacher_id =" + teacherId);
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

	/**
	 * Overloaded function to get the journal of the chosen subject
	 * 
	 * @param id      Teached id
	 * @param subject The name of the subject
	 * @return Journal list of chosen subject
	 */
	public List<Journal> getJournal(int id, String subject) {
		LinkedList<Journal> list = new LinkedList<>();
		try {
			rs = st.executeQuery(
					"SELECT course_id, student_id, mark, (select courses.subject from courses where courses.id = j.course_id)"
							+ " AS subject FROM journal j JOIN courses sc ON j.course_id = sc.id"
							+ " WHERE j.course_id IN (SELECT id FROM courses WHERE teacher_id=" + id + ") "
							+ " AND sc.subject='" + subject + "'");
			while (rs.next()) {
				list.add(new Journal(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Teacher receives the journal of students which has finished courses
	 * 
	 * @param id The id of teacher
	 * @return List of the prepared journal with marks
	 */
	public List<Journal> getJournal(int id) {
		LinkedList<Journal> list = new LinkedList<>();
		try {
			rs = st.executeQuery("SELECT course_id, student_id, mark, (select subject from courses "
					+ "where courses.id = journal.course_id) AS subject FROM journal WHERE course_id IN (SELECT id FROM "
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
			LOG.trace(
					"Course " + course.getSubject() + " of teacher(id) " + course.getTeacherId() + " has been created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCourse(Course course) {
		try {
			st.executeUpdate("UPDATE courses SET subject='" + course.getSubject() + "', start='" + course.getStart()
					+ "', end='" + course.getEnd() + "', teacher_id=" + course.getTeacherId() + " where id="
					+ course.getId());
			LOG.trace("Course with id: " + course.getId() + " " + course.getSubject() + " " + course.getStart() + " "
					+ course.getEnd() + " of teacher(login)" + course.getTeacherLogin() + " has been updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCourse(int id) {
		try {
			st.executeUpdate("DELETE FROM courses WHERE id=" + id);
			LOG.trace("course with id = " + id + " has been deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The list of distinct subjects to make non-repeating select of subjects to get
	 * certain courses from different tables
	 * 
	 * @param teacherID
	 * @return List of subject's distinct names
	 */
	public List<String> getCourses(int teacherID) {
		List<String> list = new LinkedList<String>();
		try {
			rs = st.executeQuery("select distinct subject from courses where teacher_id=" + teacherID);
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
				LOG.trace("student (id)" + id + " has been unblocked");
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
				LOG.trace("student (id)" + id + " has been blocked");
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
		try {
			rs = st.executeQuery("SELECT * FROM users where login='"+login+"'");
			if(rs.next()) {
				return null;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		switch (status) {
		case "student":
			try {
				st.executeUpdate("INSERT INTO users (login, password, role) " + "VALUES ('" + login + "', '" + password
						+ "', (SELECT roles.id FROM roles WHERE roles.role_name = 'student'))");
				rs = st.executeQuery("SELECT * FROM users ORDER BY users.id ASC");
				LOG.trace("User: " + login + " " + password + " has been registered");
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
				LOG.trace("Teacher: " + login + " " + password + " has been registered as waiting");
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
				if (status.equals("student")) {
					if (this.checkBlocked(id)) {
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
					studentSet.close();
				} else {
					list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * After teacher has applied to Sign Up He comes into teacher's register
	 * requests table And waits for administrator's approvement
	 * 
	 * @return The list of the teachers applied to be registered
	 */
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

	/**
	 * Administration approves the teacher's registration
	 * 
	 * @param login    teachers login
	 * @param password teachers password
	 * @return {@code true} if teacher has been approved or {@code false} if he
	 *         hasn't
	 */
	public boolean approveTeacher(String login, String password) {
		try {
			st.executeUpdate(
					"INSERT INTO users (login, password, role) VALUES ('" + login + "', '" + password + "', '2')");
			st.executeUpdate(
					"DELETE FROM register_request WHERE username='" + login + "' AND password='" + password + "'");
			LOG.trace("Teacher: (" + login + ", " + password + ") status has been changed from waiting to teacher");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkBlocked(int id) {
		try {
			ResultSet studCheck = st.executeQuery("SELECT id from blocked where id=" + id);
			boolean result = studCheck.next();
			studCheck.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
