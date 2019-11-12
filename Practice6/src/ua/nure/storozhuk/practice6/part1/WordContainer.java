package ua.nure.storozhuk.practice6.part1;

import java.util.Arrays;
import java.util.Scanner;

public class WordContainer {
	Word[] words;

	public WordContainer() {
	}

	public WordContainer(String[] words) {
		for (int m = 0; m < words.length; m++) {
			add(words[m]);
		}
	}

	public final boolean add(String word) {
		if (words != null) {
			for (int m = 0; m < words.length; m++) {
				if (words[m] != null && word.equals(words[m].content)) {
					words[m].frequency++;
					return false;
				}
			}
			Word[] tmp = new Word[words.length];
			System.arraycopy(words, 0, tmp, 0, words.length);
			words = new Word[tmp.length + 1];
			System.arraycopy(tmp, 0, words, 0, tmp.length);
			words[words.length - 1] = new Word(word);
			return true;
		}
		words = new Word[1];
		words[0] = new Word(word);
		return true;
	}

	public Word get() {
		Word ret = null;
		for (int m = 0; m < words.length; m++) {
			ret = words[m];
		}
		return ret;
	}

	public String toString() {
		Arrays.sort(words);
		StringBuilder str = new StringBuilder();
		for (int m = 0; m < words.length; m++) {
			str.append(words[m].content + " : " + words[m].frequency + "\n");
		}
		str.delete(str.length() - 1, str.length());
		return str.toString();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder input = new StringBuilder();
		while (scan.hasNextLine()) {
			input.append(scan.nextLine());
			input.append("\n");
			if (input.toString().contains("stop")) {
				input.delete(input.indexOf("stop"), input.length());
				break;
			}
		}
		scan.close();
		WordContainer wCont = new WordContainer(input.toString().split("[^\\w]+"));
		System.out.println(wCont);
	}

}
