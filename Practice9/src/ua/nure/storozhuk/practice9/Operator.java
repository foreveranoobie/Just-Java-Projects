package ua.nure.storozhuk.practice9;

public class Operator {
	public int calculate(String operand, int first, int second) {
		final int[] operands = { 0x2b, 0x2d };
		for (int curOperand : operands) {
			if (curOperand == operand.charAt(0)) {
				switch (curOperand) {
				case 0x2b:
					return calcSum(first, second);
				case 0x2d:
					return calcDif(first, second);
				default:
					break;
				}
			}
		}
		return Integer.valueOf(operand.charAt(0)) == 0x3D ? 0x3d : 0;
	}

	private int calcDif(int first, int second) {
		return first - second;
	}

	private int calcSum(int first, int second) {
		return first + second;
	}
}
