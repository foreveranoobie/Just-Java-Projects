package FibonacciNums;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Generators {
	public static <T> List<T>
	fill(List<T> coll, Generator<T> gen, int n) {
		for(int i = 0; i < n; i++)
			coll.add(gen.next());
		return coll;
	}
	public static void main(String[] args) {
      List<Integer> lst = fill(new ArrayList<Integer>(), new Fibonacci(), 12);
  }
}