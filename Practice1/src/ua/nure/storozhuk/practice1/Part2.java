package ua.nure.storozhuk.practice1;

public class Part2 {
	public static void main(String... args) {
		if (args.length < 2) {
			System.out.println("There are fewer than two arguments");
		} else {
			int x = Integer.parseInt(args[0]);
			for (int i = 1; i < args.length; i++) {
				x += Integer.parseInt(args[i]);
			}
			System.out.println(x);
		}
	}
}