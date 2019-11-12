package ua.nure.storozhuk.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part62 {
	public static void main(String[] strings) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(strings[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
			if (index == -1) {
				set.add(new Part6Words(group, group.length()));
			}
		}
		Collections.sort(set, new Part6Words.NumComparator());
		for (int l = 0; l < 3; l++) {
			System.out.println(set.get(l).word + " ==> " + set.get(l).num);
		}
		scan.close();
	}
}
