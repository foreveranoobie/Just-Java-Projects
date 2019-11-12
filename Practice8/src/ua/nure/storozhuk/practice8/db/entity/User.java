package ua.nure.storozhuk.practice8.db.entity;

public class User{
	private String login;
	private int id;

	public User(int id, String name) {
		this.id = id;
		this.login = name;
	}

	public User(String login) {
		this.login = login;
	}

	public User() {
	}
	
	 @Override
	    public int hashCode() {
	        return this.login.codePointCount(0, login.length());
	    }
	
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
		    return false;
		}
		return this.login.equals(((User) obj).getLogin());
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public static User createUser(String login) {
		return new User(login);
	}

	public String toString() {
		return login;
	}

	public int getId() {
		return id;
	}

}
