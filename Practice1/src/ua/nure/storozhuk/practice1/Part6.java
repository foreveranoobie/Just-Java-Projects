package ua.nure.storozhuk.practice1;

public class Part6 {
	static boolean isSimple(int num) {
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String... args) {
		if (args != null && args.length == 1 && Integer.parseInt(args[0]) > 0) {
			int size = Integer.parseInt(args[0]);
			int[] arr = new int[size];
			int count = 0;
			int number = 2;
			while (count < size) {
				if (isSimple(number++)) {
					arr[count++] = number - 1;
				}
			}
			for (int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
		} else {
			System.out.println("Something wrong with your arguments: check whether "
					+ "you wrote only 1 argument of additional type");
		}
	}

}
