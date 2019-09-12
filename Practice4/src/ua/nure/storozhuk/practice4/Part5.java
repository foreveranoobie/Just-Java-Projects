package ua.nure.storozhuk.practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

	public static void main(String[] args) {
		ResourceBundle mybundle = ResourceBundle.getBundle("Words");
		Scanner scan = new Scanner(System.in);
		String[] answer = new String[2];
		while (scan.hasNext()) {
			answer = scan.nextLine().split(" ");
			if ("stop".equals(answer[0])) {
				break;
			} else if (answer.length < 2) {
				System.out.println("No such values");
				continue;
			}
			mybundle = ResourceBundle.getBundle("resources", new Locale(answer[1]));
			try {
				System.out.println(mybundle.getString(answer[0]));
			} catch (MissingResourceException e) {
				System.out.println("No such values");
			}
		}
		scan.close();
	}

}
