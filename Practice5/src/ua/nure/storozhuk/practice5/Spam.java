package ua.nure.storozhuk.practice5;

import java.util.Scanner;

public class Spam {
	private Thread[] threads;
	private String[] messages;
	private int[] delay;

	public Spam(String[] messages, int[] delay) {
		this.messages = new String[messages.length];
		System.arraycopy(messages, 0, this.messages, 0, messages.length);
		this.delay = new int[delay.length];
		System.arraycopy(delay, 0, this.delay, 0, delay.length);
		threads = new Worker[messages.length];
	}

	public void start() {
		for (int i = 0; i < threads.length; i++) {
			threads[i] = threads[i] = new Worker(messages[i], delay[i]);
			threads[i].start();
		}
	}

	public void stop() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].interrupt();
		}
	}

	private static class Worker extends Thread {
		String message;
		int delay;

		public Worker(String message, int delay) {
			this.message = message;
			this.delay = delay;
		}

		public void run() {
			for (;;) {
				try {
					System.out.println(Thread.currentThread().getName() + " --> " + message);
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		String[] txt = { "One", "Two", "Three", "Four" };
		int[] del = { 700, 500, 200, 800 };
		Spam spam = new Spam(txt, del);
		Scanner scan = new Scanner(System.in);
		spam.start();
		if (scan.hasNextLine()) {
			spam.stop();
		}
		scan.close();
	}
}
