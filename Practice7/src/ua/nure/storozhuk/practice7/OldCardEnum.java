package ua.nure.storozhuk.practice7;

public enum OldCardEnum {
	OLDCARDS("OldCards"), OLDCARD("OldCard"), THEMA("Thema"), AUTHOR("Author"), YEAR("Year"), 
	VALUABLE("Valuable"), COUNTRY("Country"), TYPE("Type");
	private String value;

	OldCardEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
