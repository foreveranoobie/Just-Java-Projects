package ua.nure.storozhuk.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
	private static final String ENCODING = "Cp1251";

	public static String convert(String input) {
		Pattern p = Pattern.compile("\\b([A-Za-zР-пр-џ]+)\\b([\\s\\w]+?(\\b\\1\\b)[\\s\\w]*?)+",
				Pattern.MULTILINE | Pattern.UNICODE_CHARACTER_CLASS);
		Matcher m = p.matcher(input);
		while (m.find()) {
			input = input.replaceAll("\\b" + m.group(1) + "\\b", "_" + m.group(1));
			m = p.matcher(input);
		}
		return input; 
	}

	public static void main(String... strings) {
		String input = readFile("part6.txt");
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
