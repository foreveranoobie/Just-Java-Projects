package innerclasses;

interface Selector {
	  boolean end();
	  Object current();
	  void next();
	}	

	public class Sequence {
	  private Object[] items;
	  private int next = 0;
	  public Sequence(int size) throws MyException { items = new Object[size]; 
	    if(size > 10)
	    	throw new MyException("Big size of array");
	  }
	  public void add(Object x) {
	    if(next < items.length)
	      items[next++] = x;
	  }
	  private class SequenceSelector implements Selector {
	    private int i = 0;
	    public boolean end() { return i == items.length; }
	    public Object current() { return items[i]; }
	    public void next() { if(i < items.length) i++; }
	  }
	  public Selector selector() {
	    return new SequenceSelector();
	  }	
	  public static void main(String[] args) throws MyException {
	    Sequence sequence = new Sequence(10);
	    for(int i = 0; i < 10; i++)
	      sequence.add(Integer.toString(i));
	    Selector selector = sequence.selector();
	    while(!selector.end()) {
	      System.out.print(selector.current() + " ");
	      selector.next();
	    }
	  }
	}
class MyException extends Exception{
	MyException(String txt){super(txt);}
}