package InterfTests;

public class ImplOne implements One{
	public void f() {
		System.out.println("ImplOne.f()");
	}
	public void e() {
		System.out.println("ImplOne.e()");
	}
	public void g() {
		System.out.println("ImplOne.g() not implemented");
	}
}
