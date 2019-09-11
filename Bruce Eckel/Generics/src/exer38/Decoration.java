package exer38;

class BasicCoffee {
   private String value;
   public void set(String val) {
	   value = val;
   }
   public String get() {
	   return value;
   }
}

class Decorator extends BasicCoffee{
	protected BasicCoffee basic;
	public Decorator(BasicCoffee basic) {this.basic = basic;}
	public void set(String val) {basic.set(val);}
    public String get() {return basic.get();}
}

class Americano extends Decorator{
	public Americano(BasicCoffee basic) {
		super(basic);
		// TODO Auto-generated constructor stub
	}
	public String getAmericano() {
		return this.getClass().getSimpleName();
	}
}

class Foam extends Decorator{

	public Foam(BasicCoffee basic) {
		super(basic);
		// TODO Auto-generated constructor stub
	}
    public String getFoam() {
    	return this.getClass().getSimpleName();
    }	
}

class Milked extends Decorator{

	public Milked(BasicCoffee basic) {
		super(basic);
		// TODO Auto-generated constructor stub
	}
    public String getMilked() {
    	return this.getClass().getSimpleName();
    }	
}

public class Decoration{
	public static void main(String...strings) {
		Milked mlk = new Milked(new BasicCoffee());
		System.out.println(mlk.getMilked());
		Foam foam = new Foam(new Milked(new BasicCoffee()));
		System.out.println(foam.getFoam());
	}
}