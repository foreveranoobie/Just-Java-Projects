package ua.nure.storozhuk.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
	int[] array;

	public Range(int low, int max) {
		int size = max - low + 1;
		array = new int[size];
		for (int m = 0; m < size; m++) {
			array[m] = m + low;
		}
	}

	public Range(int low, int max, boolean reverse) {
		if (!reverse) {
			int size = max - low + 1;
			array = new int[size];
			for (int m = 0; m < size; m++) {
				array[m] = low++;
			}
		} else {
			int size = max - low + 1;
			array = new int[size];
			for (int m = 0; m < size; m++) {
				array[m] = max--;
			}
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			int curIndex;

			@Override
			public boolean hasNext() {
				return curIndex < array.length;
			}

			@Override
			public Integer next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				if (this.hasNext()) {
					return array[curIndex++];
				}
				return null;
			}
		};
	}
}
