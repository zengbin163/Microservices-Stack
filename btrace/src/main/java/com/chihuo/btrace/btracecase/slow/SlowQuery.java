package com.chihuo.btrace.btracecase.slow;

public class SlowQuery {

	public int sum() {
		
		int result = 0;
		for (int i = 0; i < 100; i++) {
			result += i * i;
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
		
		return result;
	}

	public static void main(String[] args) {
		while (true) {
			Thread.currentThread().setName("计算");
			SlowQuery util = new SlowQuery();
			int result = util.sum();
			System.out.println(result);
		}
	}

}
