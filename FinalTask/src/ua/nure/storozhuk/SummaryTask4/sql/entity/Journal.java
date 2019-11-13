package ua.nure.storozhuk.SummaryTask4.sql.entity;

import java.util.Comparator;

/**
 * Journal entity
 * @author Alex
 *
 */
public class Journal {
	private int courseId;
	private int studentId;
	private int mark;
	private String subject;

	public Journal(int courseId, int studentId, int mark, String subject) {
		this.courseId = courseId;
		this.studentId = studentId;
		this.mark = mark;
		this.subject = subject;
	}

	public Journal() {
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getCourseId() {
		return courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public int getMark() {
		return mark;
	}

	public String getSubject() {
		return subject;
	}
	
	public static Comparator<Journal> markDescComparator(){
		return new Comparator<Journal>(){
			@Override
			public int compare(Journal o1, Journal o2) {
				return -(o1.getMark() - o2.getMark());
			}};
	}
	
	public static Comparator<Journal> markComparator(){
		return new Comparator<Journal>(){
			@Override
			public int compare(Journal o1, Journal o2) {
				return o1.getMark() - o2.getMark();
			}};
	}
	
	public static Comparator<Journal> subjectDescComparator(){
		return new Comparator<Journal>(){
			@Override
			public int compare(Journal o1, Journal o2) {
				return -(o1.getSubject().compareTo(o2.getSubject()));
			}};
	}
	
	public static Comparator<Journal> subjectComparator(){
		return new Comparator<Journal>(){
			@Override
			public int compare(Journal o1, Journal o2) {
				return o1.getSubject().compareTo(o2.getSubject());
			}};
	}

}
