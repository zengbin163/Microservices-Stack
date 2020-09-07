package com.chihuo.webflux;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface WorkerInterface {
	public void doSomeWork();
}

public class LambdaTest {

	public static void execute(WorkerInterface worker) {
		worker.doSomeWork();
	}

	public void test() {
		String[] atp = { "Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer",
				"Andy Murray", "Tomas Berdych", "Juan Martin Del Potro" };
		List<String> players = Arrays.asList(atp);
		players.forEach((player) -> System.out.println(player + "; "));

		execute(new WorkerInterface() {
			@Override
			public void doSomeWork() {
				System.out.println("Worker invoked using Anonymous class");
			}
		});
		execute(() -> System.out.println("Worker invoked using Lambda expression"));
	}

	public static void main(String[] args) {
		LambdaTest test = new LambdaTest();
		test.test();
	}
}
