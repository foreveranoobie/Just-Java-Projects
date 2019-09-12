package ua.nure.storozhuk.practice1;

public class Part4 {
	static void findCom(int fewer, int bigger) {
		for (int m = fewer; m > 1; m--) {
			if (fewer % m == 0 && bigger % m == 0) {
				System.out.println(m);
				return;
			}
		}
		System.out.println(0);
	}

	public static void main(String... args) {
		if (args.length == 2) {
			if (Integer.parseInt(args[0]) > Integer.parseInt(args[1])) {
				findCom(Integer.parseInt(args[1]), Integer.parseInt(args[0]));
			} else {
				findCom(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			}
		} else {
			System.out.println("You need to write 2 arguments during start Java program!");
		}
	}
}