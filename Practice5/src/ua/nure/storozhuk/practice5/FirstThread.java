package ua.nure.storozhuk.practice5;

public class FirstThread implements Runnable {

	@Override
	public void run() {
		boolean reas = true;
		while (reas) {
			try {
				System.out.println(Thread.currentThread().getName());
				Thread.sleep(1000 / 3);
				reas = true;
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " work done");
				reas = false;
			}
		}
	}
}