
public class Exc {
    private static void doExc() throws MyException {
    	throw new MyException();
    }
    private static void callExc() throws MyNextException, MyException {
    	try {
    	doExc();
    	} catch(Exception e) {
    		e.printStackTrace();
    		e.getMessage();
    		throw new RuntimeException(e);
    	}
    }
	public static void main(String[] args) throws MyNextException, MyException {
		doExc();
		System.out.println("4");
	}

}

class MyException extends Exception{
	public MyException() {}
	public MyException(String str) {
		super(str);
	}
	public String getMessage() {
		return "This is my Exception";
	}
}

class MyNextException extends Exception{
	public MyNextException() {}
	public String getMessage() {
		return "This is my next Exception";
	}
}