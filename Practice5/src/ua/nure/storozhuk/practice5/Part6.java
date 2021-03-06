package ua.nure.storozhuk.practice5;

public class Part6 {
	private static final Object M = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread() {
			public void run() {
				synchronized (M) {
					try {
						M.wait();
						M.notifyAll();
						M.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
		t.join(30);
		synchronized (M) {
			M.notifyAll();
			System.out.println(t.getState());
			M.wait();
			System.out.println(t.getState());
			M.notifyAll();
		}
		t.interrupt();
		t.join();
		System.out.println(t.getState());
	}

}
