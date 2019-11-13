package ua.nure.storozhuk.SummaryTask4.sql.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
/**
 * Course information
 * @author Alex
 *
 */
public class Course{
	private int id;
	private String subject;
	private String start;
	private String end;
	private int teacherId;
    private String teacherLogin;
	public Course() {
	}

	public Course(int id, String subject, String start, String end, int teacherId, String teacherLogin) {
		this.id = id;
		this.subject = subject;
		this.start = start;
		this.end = end;
		this.teacherId = teacherId;
		this.teacherLogin = teacherLogin;
	}
	
	public String getTeacherLogin() {
		return teacherLogin;
	}
	
	public void setTeacherLogin(String teacherLogin) {
		this.teacherLogin = teacherLogin;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public int getTeacherId() {
		return teacherId;
	}
	
	/**
	 * 
	 * @return the time difference between end and start dates
	 */
	private int timeDif() {
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(getStart());
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(getEnd());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (date2.getTime() - date1.getTime());
	}
	
	public static Comparator<Course> termDescComparator() {
		return new Comparator<Course>() {

			public int compare(Course o1, Course o2) {
				return -(o1.timeDif() - o2.timeDif());
			}};
	}
	
	public static Comparator<Course> termComparator() {
		return new Comparator<Course>() {

			public int compare(Course o1, Course o2) {
				return o1.timeDif() - o2.timeDif();
			}};
	}
	
	public static Comparator<Course> subjectDescComparator() {
		return new Comparator<Course>() {

			public int compare(Course o1, Course o2) {
				return -(o1.getSubject().compareTo(o2.getSubject()));
			}};
	}

	public static Comparator<Course> subjectComparator() {
		return new Comparator<Course>() {

			public int compare(Course o1, Course o2) {
				// TODO Auto-generated method stub
				return o1.getSubject().compareTo(o2.getSubject());
			}};
	}
}
