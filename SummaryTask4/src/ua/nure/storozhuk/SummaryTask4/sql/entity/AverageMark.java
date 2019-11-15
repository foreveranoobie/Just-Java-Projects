package ua.nure.storozhuk.SummaryTask4.sql.entity;

public class AverageMark {
	private int studentId;
	private float mark;
	private String login;
	public AverageMark() {}
	public AverageMark(int studentId, float mark, String login) {
		this.studentId = studentId;
		this.mark = mark;
		this.login = login;
	}
	public int getStudentId() {
		return studentId;
	}
	public float getMark() {
		return mark;
	}
	public String getLogin() {
		return login;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public void setMark(float mark) {
		this.mark = mark;
	}
	public void set(String login) {
		this.login = login;
	}
}
