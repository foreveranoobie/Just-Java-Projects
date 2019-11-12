package ua.nure.storozhuk.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;

class Recorder extends Thread {
	int num;
	int seek;
	RandomAccessFile raf;

	public Recorder(int num, int seek, RandomAccessFile raf) {
		this.num = num;
		this.seek = seek;
		this.raf = raf;
	}

	public void run() {
		for (int m = 0; m < 20; m++) {
			synchronized (raf) {
				try {
					Thread.sleep(1);
					raf.seek(seek);
					raf.write('0' + num);
					seek = (int) raf.getFilePointer();
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}