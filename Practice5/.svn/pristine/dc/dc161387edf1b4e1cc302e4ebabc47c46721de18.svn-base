package ua.nure.storozhuk.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Part5 {

	public static void main(String[] args) throws IOException, InterruptedException {
		File file = new File("part5.txt");
		if (file.exists()) {
			Path path = Paths.get("part5.txt");
			Files.delete(path);
		}
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			int[] seeks = new int[10];
			seeks[0] = 0;
			for (int m = 0; m < 9; m++) {
				raf.write(new byte[20]);
				raf.write("\n".getBytes());
				seeks[m + 1] = (int) raf.getFilePointer();
			}
			raf.write(new byte[20]);
			Recorder[] threads = new Recorder[10];
			for (int m = 0; m < 10; m++) {
				threads[m] = new Recorder(m, seeks[m], raf);
				threads[m].start();
			}
			for (int m = 0; m < 10; m++) {
				threads[m].join();
			}
			raf.seek(0);
			while (raf.getFilePointer() != raf.length()) {
				System.out.println(raf.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
