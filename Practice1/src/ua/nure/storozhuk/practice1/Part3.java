package ua.nure.storozhuk.practice1;

public class Part3 {
	public static void main(String... args) {
		if (args.length != 0) {
			for (int n = 0; n < args.length - 1; n++) {
				System.out.print(args[n] + " ");
			}
			System.out.print(args[args.length - 1] + "\n");
		} else {
			System.out.println("Write arguments to see the result!");
		}
	}
}