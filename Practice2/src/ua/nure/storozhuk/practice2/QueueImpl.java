package ua.nure.storozhuk.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue{
    private Node head = new Node();
    private Node tail = new Node();
    private int size;
    public static class Node{
    	Object value;
    	Node next;
    	Node prev;
    };

	@Override
	public void enqueue(Object element) {
		if(size == 0) {
			head.value  = element;
			tail.next = head;
			head.prev = tail;
			tail.prev = null;
			head.next = null;
			tail.value = null;
		}
		else if(size==1){
			tail.value = element;
		}
		else {
			Node tmp = tail;
			tail = new Node();
			tail.next = tmp;
			tmp.prev = tail;
			tail.value = element;
		}
		size++;
	}

	@Override
	public Object dequeue() {
		if(size > 1) {
		Node temp = head;
		head = head.prev;
		head.next = null;
		size--;
		return temp.value;
		}
		else if(size == 1){
			Object obj = head.value;
			clear();
			return obj;
		}
		else {
			return null;
		}
			
	}
	
	@Override
	public void clear() {
		head = new Node();
		tail = new Node();
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return new IteratorImpl();
	}

	@Override
	public Object top() {
		// TODO Auto-generated method stub
		if(size == 0) 
			return null;
		return head.value;
	}
	public class IteratorImpl implements Iterator<Object>{
        Node cur = head;
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
				cur = cur.prev;
				pos++;
				return prev.value;
			}
			return null;
		}
		
		public void remove() {
			if(prev == null) {
				throw new IllegalStateException();
			}
			if(prev.value.equals(head.value)) {
				dequeue();
				pos--;
				prev = null;
				cur = head;
			}
			else if(prev.value.equals(tail.value))
			{
				tail = tail.next;
				tail.prev = null;
			    prev = null;
			    cur = tail;
			    size--;
			    pos--;
			}
			else {
				Node prevtmp = null;
				Node tmp = head;
				while(tmp.prev != cur) {
					prevtmp = tmp;
					tmp = tmp.prev;
				}
                prevtmp.prev = tmp.prev;
                tmp.prev.next = prevtmp;
                prev = prevtmp;
                cur = prev.prev;
                pos--;
                size--;
			}
		}
	};
	public String toString() {
		if(size==0) {
			return "[]";
		}
		else if(size==1) {
			return "["+head.value+"]";
		}
		Object[] resAr = new Object[size];
		Node tmp = tail;
		String res = "[";
		for(int m = 0; m < size-1; m++) {
			resAr[m] = tmp.value;
			tmp = tmp.next;
		}
		resAr[size-1] = head.value;
		for(int m = size-1; m > 0; m--) {
		  res+=resAr[m]+", ";
		}
		res+=resAr[0];
		return res+"]";
	}
	public static void main(String...args) {
		QueueImpl queue = new QueueImpl();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");

		Iterator it = queue.iterator();
		System.out.println(it.next());
		it.remove();
		try {
			it.remove();
		} catch (IllegalStateException ex) {
			System.out.println("exception");
		}
	}
}
