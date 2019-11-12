package ua.nure.storozhuk.practice6.part1;

public class Word implements Comparable<Word> {
	public int frequency;
	public String content;

	public Word() {
	}

	public Word(String word) {
		content = word;
		frequency = 1;
	}

	public int hashCode() {
		return content.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		return this.content == ((Word) obj).content;
	}

	@Override
	public int compareTo(Word o) {
		if (this.frequency == o.frequency) {
			return this.content.hashCode() - o.content.hashCode();
		}
		return -(this.frequency - o.frequency);
	}
}
