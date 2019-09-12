package ua.nure.storozhuk.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
	private static final String ENCODING = "Cp1251";

	public static String convert(String input) {
		Pattern p = Pattern.compile("\\b[A-Za-zÀ-ß¨²¯ªÝà-ÿ¸³¿ýº]{3,}\\b",
				Pattern.MULTILINE | Pattern.UNICODE_CHARACTER_CLASS);
		Matcher m = p.matcher(input);
		StringBuilder str = new StringBuilder().append(input);
		while (m.find()) {
			if ((97 <= m.group().codePointAt(0) && m.group().codePointAt(0) <= 122)
					|| (1072 <= m.group().codePointAt(0) && m.group().codePointAt(0) <= 1103)) {
				str.replace(m.start(), m.start()+1, m.group().substring(0, 1).toUpperCase());
				input = input.replace(m.group(),
						m.group().replaceFirst("[a-zà-ÿ¸ý¿º]", m.group().substring(0, 1).toUpperCase()));
			} else {
				str.replace(m.start(), m.start()+1, m.group().substring(0, 1).toLowerCase());
				input = input.replace(m.group(),
						m.group().replaceFirst("[A-ZÀ-ß¨Ý¯ª]", m.group().substring(0, 1).toLowerCase()));
			}
		}
		input = null;
		return str.toString();
	}

	public static void main(String... strings) {
		String input = readFile("part3.txt");
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
