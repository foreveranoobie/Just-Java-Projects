package ua.nure.storozhuk.practice2;

import java.util.Iterator;

public class ArrayImpl implements Array{
    private Object[] values;
    private int size;
    public ArrayImpl(int size) {
    	this.size = size;
    }
    public ArrayImpl() {}
	@Override
	public void clear() {
		values = null;
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void add(Object element) {
		if(size == 0) {
			values = new Object[++size];
			values[0] = element;
		}
		else {
			Object[] temp = values;
			values = new Object[++size];
			for(int m = 0; m < size-1; m++) {
				values[m] = temp[m];
			}
			values[size-1] = element;
		}
	}

	@Override
	public void set(int index, Object element) {
		if(index >= 0 && index < size) {
			values[index] = element;
		}
	}

	@Override
	public Object get(int index) {
		if(index >= 0 && index < size)
		return values[index];
		else {
		return null;
		}
	}

	@Override
	public int indexOf(Object element) {
		for(int in = 0; in < size; in++) {
			if(values[in].equals(element)) {
				return in;
			}
		}
		return -1;
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		Object[] temp = new Object[--size];
		for(int m = 0; m < index; m++) {
			temp[m] = values[m];
		}
		for(int m = index + 1; m < size + 1; m++) {
			if(m-1 < 0){
				throw new IllegalStateException();
			}
			temp[m-1] = values[m];
		}
		for(int f = 0; f < size; f++) {
			values[f] = temp[f];
		}
	}
	public String toString() {
		if(size==0) {
			return "[]";
		}
		String res = "[";
		for(int m = 0; m < size-1; m++) {
			res+=values[m]+", ";
		}
		res+=values[size-1]+"]";
		return res;
	}
    private class IteratorImpl implements Iterator<Object>{
        int curIndex;
		@Override
		public boolean hasNext() {
			return curIndex < size;
		}

		@Override
		public Object next() {
			while(hasNext()) {
				return values[curIndex++];
			}
			return null;
		}
		public void remove(){
			ArrayImpl.this.remove(--curIndex);
		}
    };
    
	@Override
    public Iterator<Object> iterator(){
    	return new IteratorImpl();
    }
	
	public static void main(String... args) throws IllegalStateException{
		ArrayImpl arr = new ArrayImpl();
		arr.add("A");
		arr.add("B");
		arr.add("C");
		Iterator<Object> it = arr.iterator();
		System.out.println(it.next());
		it.remove();
		try {
			it.remove();
		} catch (IllegalStateException ex) {
			System.out.println("exception");
		}
		}
}
