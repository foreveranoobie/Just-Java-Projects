package ItMeths;

import java.util.*;
class PetSequence {
  protected Integer[] pets = new Integer[5];
  
}

public class Methods extends PetSequence {
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      private int index = 0;
      public boolean hasNext() {
        return index < pets.length;
      }
      public Integer next() { return pets[index++]; }
      public void remove() { // Not implemented
        throw new UnsupportedOperationException();
      }
    };
  }
    public Iterable<Integer> reversed(){
    	return new Iterable<Integer>() {
    		public Iterator<Integer> iterator(){
    			return new Iterator<Integer>() {

					@Override
					public boolean hasNext() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public Integer next() {
						// TODO Auto-generated method stub
						return null;
					}
    				
    			};
    		}
    	};
    };
  public static void main(String[] args) {
    Methods nc = new Methods();
  }
}
