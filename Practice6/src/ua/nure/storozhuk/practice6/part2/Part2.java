package ua.nure.storozhuk.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {

	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<>();
		for (int m = 0; m < 10000; m++) {
			array.add(m);
		}
		LinkedList<Integer> link = new LinkedList<>();
		for (int m = 0; m < 10000; m++) {
			link.add(m);
		}
		System.out.println("ArrayList#Index: " + removeByIndex(array, 4) + " ms");
		System.out.println("LinkedList#Index: " + removeByIndex(link, 4) + " ms");
		array = new ArrayList<>();
		for (int m = 0; m < 10000; m++) {
			array.add(m);
		}
		link = new LinkedList<>();
		for (int m = 0; m < 10000; m++) {
			link.add(m);
		}
		System.out.println("ArrayList#Iterator: " + removeByIterator(array, 4) + " ms");
		System.out.println("LinkedList#Iterator: " + removeByIterator(link, 4) + " ms");
	}

	public static long removeByIterator(List<Integer> list, int k) {
		long time = System.currentTimeMillis();
		Iterator<Integer> iter = list.iterator();
		while (list.size() != 1) {
			for (int m = 0; m < k; m++) {
				if (iter.hasNext()) {
					iter.next();
				} else {
					iter = list.iterator();
					iter.next();
				}
			}
			iter.remove();
		}
		return (System.currentTimeMillis() - time);
	}

	public static long removeByIndex(List<Integer> list, int k) {
		long time = System.currentTimeMillis();
		int index = k;
		while (list.size() != 1) {
			list.remove(index - 1);
			index += (k - 1);
			if (index > list.size()) {
				while (index > list.size()) {
					index -= list.size();
				}
			}
		}
		return (System.currentTimeMillis() - time);
	}

}
