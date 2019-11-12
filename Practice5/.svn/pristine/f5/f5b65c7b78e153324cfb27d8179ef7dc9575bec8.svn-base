package ua.nure.storozhuk.practice5;

class FinderThread implements Runnable {
	String[] nums;
	Part4 mon;

	FinderThread(String str, Part4 mon) {
		nums = str.split(" ");
		this.mon = mon;
	}

	@Override
	public void run() {
		for (int pos = 0; pos < nums.length; pos++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (Integer.valueOf(nums[pos]) > mon.maxV) {
				mon.maxV = Integer.valueOf(nums[pos]);
			}
		}
	}

}