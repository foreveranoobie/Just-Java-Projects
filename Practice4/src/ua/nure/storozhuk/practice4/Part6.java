package ua.nure.storozhuk.practice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
	private static final String ENCODING = "Cp1251";

	public static void cyrlTxt(String input, String answ) {
		Pattern p = Pattern.compile("\\b[�-ߨ����-�����]+\\b", Pattern.MULTILINE);
		Matcher m = p.matcher(input);
		System.out.print(answ + ": ");
		while (m.find()) {
			System.out.print(m.group() + " ");
		}
	}

	public static void latnTxt(String input, String answ) {
		Pattern p = Pattern.compile("\\b[A-Za-z]+\\b", Pattern.MULTILINE);
		Matcher m = p.matcher(input);
		System.out.print(answ + ": ");
		while (m.find()) {
			System.out.print(m.group() + " ");
		}
	}

	public static void main(String[] args) {
		String input = readFile("part6.txt");
		Scanner scan = new Scanner(System.in);
		String answer = "";
		while (scan.hasNext() && !"stop".equals(answer)) {
			answer = scan.next();
			switch (answer.toLowerCase(Locale.getDefault())) {
			case "latn":
				latnTxt(input, answer);
				System.out.println();
				break;
			case "cyrl":
				cyrlTxt(input, answer);
				System.out.println();
				break;
			case "stop":
				break;
			default:
				System.out.println(answer + ": Incorrect input");
				break;
			}
		}
		scan.close();
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
