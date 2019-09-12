
public class DataChngImpl implements DataChng {
	public void dataOffer(int ID, String name, boolean result) {
		if (result == true)
			System.out.println("Database transaction done successfully");
		else if (result == false)
			System.out.println("Database transaction failed");
	}
}
