package SmartHouse;

import java.util.*;
	

	public class Sequence {
	  private ArrayList<Object> itemsL = new ArrayList<Object>();
	  private int size = 0;
	  private int next = 0;
	  public Sequence(int size) { this.size = size; }
	  public void add(Object x) {
	    if(next < size)
	      itemsL.add(x);
	  }
	  protected void getIt() {
	     getEls(itemsL.iterator());
	  }
	  protected void getEls(Iterator <Object> iter) {
		  while(iter.hasNext()) {
			  Object obj = iter.next();
			  System.out.println(obj);
		  }
	  }
	  public static void main(String[] args) {
	    Sequence sequence = new Sequence(10);
	    for(int i = 0; i < 10; i++)
	      sequence.add(Integer.toString(i));
	    sequence.getIt();
	  }
	}
