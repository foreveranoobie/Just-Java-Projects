package ua.nure.storozhuk.SummaryTask4.sql.entity;

import java.util.Comparator;
/**
 * Students applied for a course
 * @author Alex
 *
 */
public class StudentsCourse {
	private int courseId;
	private int studentId;
	private String subject;
    private String status;
	public StudentsCourse() {
	}

	public StudentsCourse(int courseId, int studentId, String status, String subject) {
		this.courseId = courseId;
		this.studentId = studentId;
		this.status = status;
		this.subject = subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public String getStatus() {
		return status;
	}

	public int getCourseId() {
		return courseId;
	}

	public int getStudentId() {
		return studentId;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public static Comparator<StudentsCourse> subjectsDescComparator(){
		return new Comparator<StudentsCourse>() {

			@Override
			public int compare(StudentsCourse o1, StudentsCourse o2) {
				return -(o1.getSubject().compareTo(o2.getSubject()));
			}};
	}
	
	public static Comparator<StudentsCourse> subjectsComparator(){
		return new Comparator<StudentsCourse>() {

			@Override
			public int compare(StudentsCourse o1, StudentsCourse o2) {
				return o1.getSubject().compareTo(o2.getSubject());
			}};
	}
}
