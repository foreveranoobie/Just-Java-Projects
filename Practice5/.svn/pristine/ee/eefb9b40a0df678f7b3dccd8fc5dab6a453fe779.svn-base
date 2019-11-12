package ua.nure.storozhuk.practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part4 {
	int max;
	int maxV;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("part4.txt"));
		StringBuilder input = new StringBuilder();
		while (scan.hasNextLine()) {
			input.append(scan.nextLine() + "\r\n");
		}
		input.delete(input.length() - 2, input.length());
		String[] rows = input.toString().split("\r\n");
		FinderThread[] threads = new FinderThread[rows.length];
		Thread[] startThreads = new Thread[threads.length];
		Part4 mon = new Part4();
		for (int m = 0; m < rows.length; m++) {
			rows[m] = rows[m].trim();
			threads[m] = new FinderThread(rows[m], mon);
			startThreads[m] = new Thread(threads[m]);
		}
		long time = System.currentTimeMillis();
		for (Thread thread : startThreads) {
			thread.start();
		}
		for (Thread thread : startThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		time = System.currentTimeMillis() - time;
		System.out.println(mon.maxV);
		System.out.println(time);
		time = System.currentTimeMillis();
		for (String row : rows) {
			for (String num : row.split(" ")) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (Integer.valueOf(num) > mon.max) {
					mon.max = Integer.valueOf(num);
				}
			}
		}
		time = System.currentTimeMillis() - time;
		System.out.println(mon.max);
		System.out.print(time);
		scan.close();
	}
}
