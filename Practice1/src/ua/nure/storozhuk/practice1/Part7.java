package ua.nure.storozhuk.practice1;

public class Part7 {
	public static String int2str(int number) {
		final int strNums = 26;
		StringBuilder colName = new StringBuilder();
		while (number > 0) {
			int rem = number % strNums;
			if (rem == 0) {
				colName.append("Z");
				number = (number / strNums) - 1;
			} else {
				colName.append(((char) ((rem - 1) + 'A')));
				number = number / strNums;
			}
		}
		StringBuilder result = new StringBuilder();
		for (int i = colName.length() - 1; i > -1; i--) {
			result.append(Character.toString(colName.toString().charAt(i)));
		}
		return result.toString();
	}

	public static int str2int(String s) {
		int result = 0;
		final int strNums = 26;
		for (char c : s.toCharArray()) {
			result = result * strNums + (c - 'A') + 1;
		}
		return result;
	}

	public static String rightColumn(String number) {
		String resultCh;
		resultCh = int2str(str2int(number) + 1);
		return resultCh;
	}

	public static void main(String... args) {
		printStr("A");
		printStr("B");
		printStr("Z");
		printStr("AA");
		printStr("AZ");
		printStr("BA");
		printStr("ZZ");
		printStr("AAA");
	}

	public static void printStr(String st) {
		int num = str2int(st);
		System.out.print(st + " ==> " + num + " ==> " + int2str(num) + "\n");
	}

}
