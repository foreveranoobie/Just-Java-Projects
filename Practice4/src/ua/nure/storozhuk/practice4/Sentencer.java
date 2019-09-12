package ua.nure.storozhuk.practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Sentencer implements Iterable<String> {
	String[] sentences;
	int size;

	public Sentencer(String[] strs) {
		sentences = strs;
		size = strs.length;
		for (int m = 0; m < size; m++) {
			sentences[m] = sentences[m].trim();
			sentences[m] = sentences[m].replaceAll("[\\r\\n]", "");
		}
		if ("".equals(sentences[size - 1])) {
			String[] tmp = new String[--size];
			for (int m = 0; m < size; m++) {
				tmp[m] = sentences[m];
			}
			sentences = new String[size];
			sentences = tmp;
		}
	}

	class SentIter implements Iterator<String> {
		int cur;

		@Override
		public boolean hasNext() {
			return cur < size;
		}

		@Override
		public String next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return sentences[cur++];
		}

	}

	@Override
	public Iterator<String> iterator() {
		return new SentIter();
	}
}