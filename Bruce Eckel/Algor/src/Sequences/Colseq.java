package Sequences;

import java.util.Collection;
import java.util.Iterator;

public class Colseq<T> implements Collection{
    Object[] array;
    int size;
    public Colseq(int size) {
    	this.size = size;
    	array = new Object[size];
    }
    public Colseq() {
    	size = 0;
    }
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size > 0 ? false : true);
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public Iterator iterator() {
		return new Iterator<T>(){
            int curr = 0;
			@Override
			public boolean hasNext() {
				if(array[curr+1]!=null) {
					return true;
				}
				return false;
			}

			@Override
			public T next() {
				if(this.hasNext()) {
				Colseq<T> cl = Colseq.this;
				curr++;
				return (T)cl.array[curr];
				}
				return null;
			}
		};
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return array;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		Object obj[] = c.toArray();
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
    
}
