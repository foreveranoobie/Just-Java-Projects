package ua.nure.storozhuk.practice5;

import java.io.IOException;
import java.io.InputStream;

class InputStreamer extends InputStream {
	int counter;

	public int read() throws IOException {
		if (counter == 0) {
			try {
				Thread.sleep(2000);
				counter++;
				return 10;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
}