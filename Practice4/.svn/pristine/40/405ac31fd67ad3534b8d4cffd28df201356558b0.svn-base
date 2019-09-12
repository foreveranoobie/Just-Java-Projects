package ua.nure.storozhuk.practice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Part4 {
	private static final String ENCODING = "Cp1251";

	public static void main(String[] args) {
		String input = readFile("part4.txt");
		String[] sentences;
		if (input != null) {
			sentences = input.split("(?<=[?.!])", Pattern.MULTILINE | Pattern.UNICODE_CHARACTER_CLASS);
			Sentencer stnc = new Sentencer(sentences);
			for (String str : stnc) {
				System.out.println(str);
			}
		}
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
