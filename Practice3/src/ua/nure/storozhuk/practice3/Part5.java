package ua.nure.storozhuk.practice3;

public class Part5 {
	static String[] romans = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C" };
	static int[] nums = { 1, 4, 5, 9, 10, 40, 50, 90, 100 };

	public static String decimal2Roman(int x) {
		StringBuilder romanNumber = new StringBuilder();
		for (int m = nums.length - 1; m > -1; m--) {
			while (x >= nums[m]) {
				romanNumber.append(romans[m]);
				x = x - nums[m];
			}
		}
		return romanNumber.toString();
	}

	public static int roman2Decimal(String s) {
		int result = 0;
		String[] roms = s.split("");
		outer: for (int m = 0; m < roms.length; m++) {
			for (int k = romans.length - 1; k > -1; k--) {
				if (m != roms.length - 1 && romans[k].equals(roms[m] + roms[m + 1])) {
					result += nums[k];
					m++;
					continue outer;
				} else if (romans[k].equals(roms[m])) {
					result += nums[k];
					continue outer;
				}
			}
		}
		return result;
	}

	public static void main(String...strings) {
		String str = new String();
		for (int i = 1; i < 100; i++) {
			str = decimal2Roman(i);
			System.out.print(i + " --> " + decimal2Roman(i) + " --> " + roman2Decimal(str) + "\n");
		}
		str = decimal2Roman(100);
		System.out.print(100 + " --> " + decimal2Roman(100) + " --> " + roman2Decimal(str));
	}
}
