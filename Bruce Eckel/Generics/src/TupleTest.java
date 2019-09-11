
public class TupleTest {
    static <A, B> TwoTuple<A,B> f(A a, B b){
    	return Tuple.tup(a, b);
    }
	public static void main(String[] args) {
		TwoTuple<String, Integer> two = f("Hello", 1);
		System.out.println(two);
	}

}

class Tuple{
	public static<A, B> TwoTuple<A, B> tup(A aa, B bb){
		return new TwoTuple<A,B>(aa, bb);
	}
}

class TwoTuple<A, B>{
	public TwoTuple(A aa, B bb){
		first = aa;
		second = bb;
	}
	A first;
	B second;
	public String toString() {
		return first+" "+second;
	}
}
