package ua.nure.storozhuk.SummaryTask4.sql.entity;
/**
 * User information
 * @author Alex
 *
 */
public class User {
	private int id;
	private String login, password, status;

	public User(int id, String login, String password, String status) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getStatus() {
		return this.status;
	}
}
