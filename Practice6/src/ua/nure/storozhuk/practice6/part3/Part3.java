package ua.nure.storozhuk.practice6.part3;

public class Part3 {

	public static void main(String[] args) {
		Parking parking = new Parking(5);
		try {
			parking.arrive(5);
		} catch (IllegalArgumentException ex) {
			System.out.println("IllegalArgumentException");
		}
	}

}
