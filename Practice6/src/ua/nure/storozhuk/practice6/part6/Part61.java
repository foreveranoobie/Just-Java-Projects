package ua.nure.storozhuk.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part61 {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(new File(args[0]))) {
			StringBuilder input = new StringBuilder();
			while (scan.hasNextLine()) {
				input.append(scan.nextLine() + " ");
			}
			ArrayList<Part6Words> set = new ArrayList<>();
			Pattern p = Pattern.compile("\\b[A-Za-z]+\\b");
			Matcher m = p.matcher(input.toString());
			int index = 0;
			String group = "";
			while (m.find()) {
				group = m.group();
				index = set.indexOf(new Part6Words(group, 0));
				if (index != -1) {
					set.set(index, new Part6Words(set.get(index).word, set.get(index).num + 1));
				} else {
					set.add(new Part6Words(group, 1));
				}
			}
			Collections.sort(set, new Part6Words.NumComparator());
			Part6Words[] words = new Part6Words[3];
			for (int cnt = 0; cnt < 3; cnt++) {
				words[cnt] = set.get(cnt);
			}
			Arrays.sort(words, new Part6Words.StrComparator());
			for (Part6Words word : words) {
				System.out.println(word.word + " ==> " + word.num);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
