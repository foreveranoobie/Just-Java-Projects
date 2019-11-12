package ua.nure.storozhuk.practice8.db.entity;

public class Team{
	private String name;
	private int id;

	public Team(String name) {
		this.name = name;
	}

	public Team(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	 @Override
	    public int hashCode() {
	        return this.name.codePointCount(0, name.length());
	    }
	
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
		    return false;
		}
		return this.name.equals(((Team) obj).getName());
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Team() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public static Team createTeam(String name) {
		return new Team(name);
	}

	public String toString() {
		return name;
	}

}
