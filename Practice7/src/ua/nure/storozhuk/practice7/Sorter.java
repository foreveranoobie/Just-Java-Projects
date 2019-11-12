package ua.nure.storozhuk.practice7;

import java.util.Comparator;

public class Sorter {
	public Comparator<OldCard> yearComp() {
		return new Comparator<OldCard>() {
			@Override
			public int compare(OldCard o1, OldCard o2) {
				return o1.getYear() - o2.getYear();
			}
		};
	}

	public Comparator<OldCard> authorComp() {
		return new Comparator<OldCard>() {
			@Override
			public int compare(OldCard o1, OldCard o2) {
				if (o1.getAuthor() != null && o2.getAuthor() != null) {
					return o1.hashCode() - o2.hashCode();
				} else {
					return 1;
				}
			}
		};
	}

	public Comparator<OldCard> typeComp() {
		return new Comparator<OldCard>() {
			@Override
			public int compare(OldCard o1, OldCard o2) {
				return o1.getType().compareTo(o2.getType());
			}
		};
	}
}
