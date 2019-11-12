package ua.nure.storozhuk.practice6.part6;

import java.io.Serializable;
import java.util.Comparator;

public class Part6Words {
	public String word;
	public int num;

	public Part6Words(String str, int num) {
		word = str;
		this.num = num;
	}

	public Part6Words() {
	}

	public int hashCode() {
		int result = 32;
		result = result * 4 + word.hashCode();
		return result;
	}

	public boolean equals(String obj) {
		return this.word.equals(obj);
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.getClass() == obj.getClass() && obj.hashCode() == this.hashCode()) {
			String compare = ((Part6Words) obj).word;
			if (this.word.equals(compare)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return word + " : " + num;
	}

	public static class StrComparator implements Comparator<Part6Words>, Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public int compare(Part6Words o1, Part6Words o2) {
			return -(o1.word.compareTo(o2.word));
		}
	}

	public static class NumComparator implements Comparator<Part6Words>, Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Part6Words o1, Part6Words o2) {
			return -(o1.num - o2.num);
		}
	}
}
