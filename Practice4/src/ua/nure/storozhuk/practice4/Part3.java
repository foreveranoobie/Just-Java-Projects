package ua.nure.storozhuk.practice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
	private static final String ENCODING = "Cp1251";
	private static final String EXCEPT = "No such values";

	public static void getInt(String input) {
		Pattern p = Pattern.compile("([\\s\\w]\\b\\d+\\b[\\s\\w])|(0x\\d+[^A-Za-z])", Pattern.MULTILINE);
		Matcher m = p.matcher(input);
		while (m.find()) {
			System.out.print(m.group().trim() + " ");
		}
		System.out.println();
	}

	public static void getDouble(String input) {
		Pattern p = Pattern.compile("[^A-Za-z�-��-�](\\d*\\.\\d+)|(\\d+\\.\\d*)|(\\d+\\.\\d+)[^A-Za-z�-��-�]",
				Pattern.MULTILINE);
		Matcher m = p.matcher(input);
		boolean found = false;
		while (m.find()) {
			found = true;
			System.out.print(m.group().trim() + " ");
		}
		if (!found) {
			System.out.println(EXCEPT);
			return;
		}
		System.out.println();
	}

	public static void getChar(String input) {
		Pattern p = Pattern.compile("\\b[�-ߨ����-�����A-Za-z]\\b", Pattern.MULTILINE);
		Matcher m = p.matcher(input);
		boolean found = false;
		while (m.find()) {
			found = true;
			System.out.print(m.group() + " ");
		}
		if (!found) {
			System.out.println(EXCEPT);
			return;
		}
		System.out.println();
	}

	public static void getString(String input) {
		Pattern p = Pattern.compile("[A-Z�-ߨ���a-z�-�����]{2,}", Pattern.MULTILINE);
		Matcher m = p.matcher(input);
		boolean found = false;
		while (m.find()) {
			found = true;
			System.out.print(m.group() + " ");
		}
		if (!found) {
			System.out.println(EXCEPT);
			return;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String input = readFile("part3.txt");
		Scanner scan = new Scanner(System.in);
		String entry = scan.nextLine();
		while (!"stop".equals(entry)) {
			switch (entry) {
			case "int":
				getInt(input);
				break;
			case "double":
				getDouble(input);
				break;
			case "char":
				getChar(input);
				break;
			case "String":
				getString(input);
				break;
			case "stop":
				break;
			default:
				System.out.print("Incorrect input\n");
				break;
			}
			entry = scan.nextLine();
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
