package ua.nure.storozhuk.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
	private static final String ENCODING = "Cp1251";

	public static String convert(String input) {
		int maxWord = 0;
		Pattern p = Pattern.compile("[A-Za-z�-ߨ�����-������]+", Pattern.MULTILINE | Pattern.UNICODE_CHARACTER_CLASS);
		Matcher m = p.matcher(input);
		while (m.find()) {
			if (m.group().length() > maxWord) {
				maxWord = m.group().length();
			}
		}
		int lowest = maxWord;
		m.reset();
		while (m.find()) {
			if (m.group().length() < lowest) {
				lowest = m.group().length();
			}
		}
		p = Pattern.compile("\\b[A-Za-z�-ߨ�����-������]{" + lowest + "}\\b",
				Pattern.MULTILINE | Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(input);
		StringBuilder smallestW = new StringBuilder().append("Min: ");
		while (m.find()) {
			if (!smallestW.toString().contains(m.group())) {
				smallestW.append(m.group() + ", ");
			}
		}
		smallestW.delete(smallestW.length() - 2, smallestW.length());
		StringBuilder longestW = new StringBuilder().append("Max: ");
		p = Pattern.compile("\\b[A-Za-z�-ߨ�����-������]{" + maxWord + "}\\b",
				Pattern.MULTILINE | Pattern.UNICODE_CHARACTER_CLASS);
		m = p.matcher(input);
		while (m.find()) {
			if (!longestW.toString().contains(m.group())) {
				longestW.append(m.group() + ", ");
			}
		}
		longestW.delete(longestW.length() - 2, longestW.length());
		return (smallestW.toString() + "\n" + longestW.toString());
	}

	public static void main(String[] strings) {
		String input = readFile("part2.txt");
		System.out.print(convert(input));
	}

	public static String readFile(String path) {
		String res = null;
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(path));
			res = new String(bytes, ENCODING);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return res;
	}
}
