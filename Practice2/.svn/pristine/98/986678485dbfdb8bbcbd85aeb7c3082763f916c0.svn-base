package ua.nure.storozhuk.practice2;

import java.util.Iterator;

public class ListImpl implements List{
    Node first;
    Node last;
    private int size;
	public static class Node{
		Object value;
		Node next;
	};
	@Override
	public void addFirst(Object element) {
		if(size == 0) {
			first = new Node();
			last = new Node();
			first.value = last.value = element;
			first.next = last.next = null;
			size++;
		}
		else if(size == 1){
		    first.value = element;
		    first.next = last;
		    size++;
		}
		else {
			Node tmp = first;
			first = new Node();
			first.value = element;
			first.next = tmp;
			size++;
		}
	}

	@Override
	public void addLast(Object element) {
		if(size == 0) {
			first = new Node();
			last = new Node();
			first.value = last.value = element;
			first.next = last.next = null;
			size++;
		}
		else if(size == 1){
		    last.value = element;
		    first.next = last;
		    size++;
		}
		else {
			Node tmp = last;
			last = new Node();
			last.value = element;
			last.next = null;
			tmp.next = last;
			size++;
		}
	}

	@Override
	public void removeFirst() {
		first = first.next;
		size--;
	}

	@Override
	public void removeLast() {
		Node tmp = first;
		while(tmp.next.next != null) {
			tmp = tmp.next;
		}
		last = tmp;
		last.next = null;
		size--;
	}
	
	@Override
	public boolean remove(Object element) {
		if(first.value.equals(element)) {
			removeFirst();
			return true;
		}
		else if(last.value.equals(element)) {
			removeLast();
			return true;
		}
		Node tmp = first;
		Node pretmp = new Node();
		while(tmp != null) {
			pretmp = tmp;
			tmp = tmp.next;
			if(tmp.value.equals(element)) {
				pretmp.next = tmp.next;
				size--;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Object getFirst() {
		// TODO Auto-generated method stub
		return first.value;
	}

	@Override
	public Object getLast() {
		// TODO Auto-generated method stub
		return last.value;
	}

	@Override
	public Object search(Object element) {
		Node tmp = first;
		while(tmp != null) {
			if(tmp.value.equals(element)) {
				return tmp.value;
			}
			tmp = tmp.next;
		}
		return null;
	}

	public int size() {
		return size;
	}
	
	public String toString() {
		if(size == 0) {
			return "[]";
		}
		String res="";
		Node tmp = first;
		res+="[";
		for(int i = 0; i<size-1; i++) {
			res += tmp.value +", ";
			tmp = tmp.next;
		}
		res += tmp.value +"]";
		return res;
	}
	public class IteratorImpl implements Iterator<Object>{
        private Node iter = first;
        private Node prIter = null;
        private int pos = 0;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return pos < size;
		}

		@Override
		public Object next() {
			if(hasNext()) {
				prIter = iter;
				iter = iter.next;
				pos++;
				return prIter.value;
			}
			return null;
		}
		
		public void remove() {
			if(prIter == null) {
				throw new IllegalStateException();
			}
			else {
				if(prIter.value.equals(first.value)) {
					ListImpl.this.removeFirst();
					iter = first;
					prIter = null;
					pos = 0;
				}
				else if(prIter.value.equals(last.value)) {
					ListImpl.this.removeLast();
				}
				else {
					ListImpl.this.remove(prIter.value);
					if(iter == prIter) {
						iter = prIter;
					}
					else {
						pos--;
					}
					prIter = null;
			}
		}
		}		
	}
	@Override
	public void clear() {
		first = first.next = null;
		last = last.next = null;
		size = 0;
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return new IteratorImpl();
	};
	public static void main(String...args) {
		ListImpl list = new ListImpl();
		list.addLast("A");
		list.addLast("B");
		list.addLast("C");
		Iterator<Object> iter = list.iterator();
		System.out.println(iter.next());
		iter.remove();
		System.out.println(iter.next());
		iter.remove();
		System.out.println(iter.next());
		iter.remove();
		System.out.println(list);
	}
}
