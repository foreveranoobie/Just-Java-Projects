package InterfTests;

public class Main {
    public static <T extends One> void func(T obj){
    	obj.e();
    	obj.f();
        ((ImplOne) obj).g();
    }
	public static void main(String[] args) {
		ImplOne imp = new ImplOne();
		func(imp);
	}

}
