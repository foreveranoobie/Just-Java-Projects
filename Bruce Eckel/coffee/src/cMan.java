import java.util.Iterator;

public class cMan {

	public static void main(String[] args) {
	    Coffee[] cfee = new Coffee[5];
	    CoffeeGenerator cGen = new CoffeeGenerator(5);
	    Iterator<Coffee> iterC = cGen.iterator();
	    int i = 0;
	    while(iterC.hasNext()) {
	    	cfee[i++] = iterC.next();
	    }
	    for(Coffee cof : cfee) {
	    	System.out.println(cof);
	    }
	    TypeCounter counter = new TypeCounter(Coffee.class);
	    for(Coffee cof : cfee) {
	    	System.out.println(cof.getClass().getSimpleName());
	    	counter.count(cof);
	    }
	    System.out.printf("\n----------\nThe result is\n"+counter);
	}

}
