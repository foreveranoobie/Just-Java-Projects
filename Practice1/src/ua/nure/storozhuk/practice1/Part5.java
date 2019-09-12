package ua.nure.storozhuk.practice1;

public class Part5 {
	public static void main(String... args) {
		if (args.length == 1 && Integer.parseInt(args[0]) > 0) {
			int a = Integer.parseInt(args[0]);
			int sum = 0;
			while (a != 0) {
				sum += a % 10;
				a /= 10;
			}
			System.out.println(sum);
		} else {
			System.out.println("You have to add only one argument of additional sign");
		}
	}
}