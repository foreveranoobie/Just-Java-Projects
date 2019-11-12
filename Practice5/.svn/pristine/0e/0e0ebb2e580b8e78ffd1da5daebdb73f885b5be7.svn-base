package ua.nure.storozhuk.practice5;

import java.io.IOException;
import java.io.InputStream;

public class Part2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		InputStream inpstream = new InputStreamer();
		System.setIn(inpstream);
		Thread t = new Thread() {
			public void run() {
				Spam.main(null);
			}
		};
		t.start();
		t.join();
		System.setIn(System.in);
		inpstream.close();
	}
}
