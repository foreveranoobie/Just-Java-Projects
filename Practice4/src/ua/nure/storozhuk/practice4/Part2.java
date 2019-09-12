package ua.nure.storozhuk.practice4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Part2 {
	static final int DIAPASONE = 50;

	public static List<Integer> sort(List<Integer> list) {
		for (int m = 0; m < list.size(); m++) {
			for (int l = m; l < list.size(); l++) {
				if (list.get(m) > list.get(l)) {
					int tmp = list.get(m);
					list.set(m, list.get(l));
					list.set(l, tmp);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) throws IOException {
		Random rand = new Random();
		FileWriter writer = new FileWriter("part2.txt");
		try (BufferedWriter buffer = new BufferedWriter(writer)) {
			for (int m = 0; m < 9; m++) {
				buffer.write(rand.nextInt(DIAPASONE) + " ");
				buffer.flush();
			}
			buffer.write(String.valueOf(rand.nextInt(DIAPASONE)));
			buffer.flush();
		}
		writer.close();
		FileReader reader = new FileReader("part2.txt");
		ArrayList<Integer> array = new ArrayList<>();
		String[] nums = null;
		try (BufferedReader breader = new BufferedReader(reader)) {
			String input = breader.readLine();
			nums = input.split(" ");
			for (String str : nums) {
				array.add(Integer.parseInt(str));
			}
		}
		reader.close();
		sort(array);
		writer = new FileWriter("part2_sorted.txt");
		try (BufferedWriter buffer = new BufferedWriter(writer)) {
			for (int m = 0; m < 9; m++) {
				buffer.write(array.get(m) + " ");
				buffer.flush();
			}
			buffer.write(String.valueOf(array.get(9)));
			buffer.flush();
		}
		writer.close();
		System.out.print("input ==> ");
		for (int m = 0; m < nums.length - 1; m++) {
			System.out.print(nums[m] + " ");
		}
		System.out.print(nums[nums.length - 1] + System.lineSeparator() + "output ==> ");
		for (int m = 0; m < array.size() - 1; m++) {
			System.out.print(array.get(m) + " ");
		}
		System.out.println(array.get(array.size() - 1));
	}

}
