package exer20;

public class Main {
    static <T extends First> void unFun(T obj){
    	obj.funFirst();
    }
    static <T extends Second> void unFun(T obj){
    	obj.funSecond();
    }
	public static void main(String[] args) {
		One on = new One();
		First on1 = on;
		Second on2 = on;
		unFun(on1);
		unFun(on2);
	}

}

class One implements First, Second{
	@Override
	public void funSecond() {
		System.out.println("One::Second");
	}

	@Override
	public void funFirst() {
       System.out.println("One::First");
	}
}