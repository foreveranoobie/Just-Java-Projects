package ua.nure.storozhuk.practice2;

import java.util.Iterator;

import ua.nure.storozhuk.practice2.QueueImpl.Node;

public class StackImpl implements Stack{
	private Node top = new Node();
	private int size;
	public static class Node{
		Object value;
		Node next;
	};
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		top = null;
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void push(Object element) {
		Node tmp = top;
		top = new Node();
		top.value = element;
		top.next = tmp;
		size++;
	}

	@Override
	public Object pop() {
		Node tmp = top;
		top = top.next;
		size--;
		return tmp.value;
	}

	@Override
	public Object top() {
		// TODO Auto-generated method stub
		return top.value;
	}
	public String toString() {
		if(size==0) {
			return "[]";
		}
		String res="[";
		Object[] elems = new Object[size];
		Node tmp = top;
		for(int i = 0; i < size; i++) {
			elems[i] = tmp.value;
		    tmp = tmp.next;
		}
		for(int i = size-1; i>0; i--) {
			res+=elems[i]+", ";
		}
		return res+elems[0]+"]";
	}
	public class IteratorImpl implements Iterator<Object>{
        Node cur = top;
        Node prev = null;
        int pos;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return pos < size;
		}

		@Override
		public Object next() {
			if(hasNext()) {
				prev = cur;
				cur = cur.next;
				pos++;
				return prev.value;
			}
			return null;
		}
		
		public void remove() {
			if(pos == 0) {
				throw new IllegalStateException();
			}
			if(prev.value.equals(top.value)) {
				pop();
				pos--;
			}
			else {
				Node prevtmp = null;
				Node tmp = top;
				while(!tmp.next.equals(cur)) {
					prevtmp = tmp;
					tmp = tmp.next;
					if(tmp.next == null) {
						throw new IllegalStateException();
					}
				}
                prevtmp.next = tmp.next;
                pos--;
                size--;
			}
		}
	};
	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return new IteratorImpl();
	}
   public static void main(String...strings) {
	   StackImpl stack = new StackImpl();
	   stack.push("A");
	   stack.push("B");
	   stack.push("C");
	   Iterator it = stack.iterator();
	   try {
	   	it.remove();
	   } catch (IllegalStateException ex) {
	   	System.out.println("exception");
	   }
   }
}
