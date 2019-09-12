package Shapes;
import java.util.*;

public class RandomShapeGenerator<T> implements Iterable<T>{
	int size;
	Object[] shp;
  RandomShapeGenerator(int size){
	  this.size = size;
	  shp = new Object[size];
	  Random rand = new Random();
	  for(int i = 0; i < size; i++) {
		  switch(rand.nextInt(2)) {
		  case 0:
			  shp[i] = new Circle();
			  break;
		  case 1:
			  shp[i] = new Square();
			  break;
		  default:
			  break;
		  }
	  }
	  }
  public void add(T t) {
	  shp[size+1] = t;
  }
@Override
public Iterator<T> iterator() {
	return new RandomIterator<T>();
}
}

class RandomIterator<E> implements Iterator{

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
