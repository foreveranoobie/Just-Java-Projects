package ua.nure.storozhuk.practice5;

public class SecondThread extends Thread {
	public void run() {
		for (;;) {
			try {
				System.out.println(Thread.currentThread().getName());
				Thread.sleep(1000 / 3);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " work done");
				break;
			}
		}
	}
}
