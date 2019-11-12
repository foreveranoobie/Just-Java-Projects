package ua.nure.storozhuk.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part63 {
	public static void main(String[] strings) {
		try (Scanner scan = new Scanner(new File(strings[0]))) {
			StringBuilder input = new StringBuilder();
			while (scan.hasNextLine()) {
				input.append(scan.nextLine() + " ");
			}
			String[] words = new String[3];
			Pattern p = Pattern.compile("\\b(\\w+)\\b", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			Matcher m = p.matcher(input.toString());
			int count = 0;
			StringBuilder str = new StringBuilder();
			while (m.find() && count < 3) {
				if (input.indexOf(m.group(), m.end()) != -1) {
					str.append(m.group().toUpperCase(Locale.ENGLISH));
					words[count++] = str.reverse().toString();
					str = new StringBuilder();
				}
			}
			for (String word : words) {
				System.out.println(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
