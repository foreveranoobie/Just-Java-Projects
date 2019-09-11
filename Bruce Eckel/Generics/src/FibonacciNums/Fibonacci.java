package FibonacciNums;

import java.util.Iterator;

public class Fibonacci implements Iterable<Integer>, Generator<Integer>{
	  private int count = 0;
	  public Integer next() { return fib(count++); }
	  public Fibonacci(int size) {
		  this.size = size;
	  }
	  public Fibonacci() {}
	  private int fib(int n) {
	    if(n < 2) return 1;
	    return fib(n-2) + fib(n-1);
	  }
	    private int size;
	    public Iterator<Integer> iterator() {
	      return new Iterator<Integer>() {
	        public boolean hasNext() { return size > 0; }
	        public Integer next() {
	          size--;
	          return Fibonacci.this.next();
	        }
	        public void remove() { // Not implemented
	          throw new UnsupportedOperationException();
	        }
	      };
	    }	
	  public static void main(String[] args) {
	    Fibonacci gen = new Fibonacci(10);
	    Iterator itr = gen.iterator();
	    while(itr.hasNext()) {
	    	System.out.println(itr.next() + " ");
	    }
	  }
}
