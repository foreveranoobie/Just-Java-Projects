package props;
class Aexc extends Exception{}
class Bexc extends Aexc{}
class Cexc extends Bexc{}
class A {
	void thrEx() throws Aexc{
		throw new Aexc();
	}
}
class B extends A {
	void thrEx() throws Bexc{
		throw new Bexc();
	}
}
class C extends B {
	void thrEx() throws Cexc{
		throw new Cexc();
	}
}
public class Main {
    static void thrF(A aa) throws Aexc {
    	aa.thrEx();
    }
	public static void main(String[] args) throws Aexc {
		C cc = new C();
		thrF(cc);
	}

}
