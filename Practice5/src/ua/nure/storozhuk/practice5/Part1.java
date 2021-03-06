package ua.nure.storozhuk.practice5;

public class Part1 {
	public static void runThird() {
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

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new FirstThread());
		thread1.start();
		Thread.sleep(1000);
		thread1.interrupt();
		SecondThread thread2 = new SecondThread();
		thread2.start();
		Thread.sleep(1000);
		thread2.interrupt();
		Thread thread3 = new Thread(Part1::runThird);
		thread3.start();
		Thread.sleep(1000);
		thread3.interrupt();
	}

}
