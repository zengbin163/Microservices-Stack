package com.chihuo.btrace.btracecase.random;

import java.util.Random;

public class RandomUtil {

	public static Random random = new Random();

	public int add(int a, int b) {
		int sum = a + b;
		System.out.println("和：" + sum);
		return a + b;
	}

	public void run() {
		try {
			while (true) {
				add(random.nextInt(10), random.nextInt(10));
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new RandomUtil().run();
	}

}
