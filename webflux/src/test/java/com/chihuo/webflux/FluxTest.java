package com.chihuo.webflux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import reactor.core.publisher.Flux;

public class FluxTest {

	public static void main(String[] args) {
		Flux.just("Hello", "World").subscribe(System.out::println);
		Flux.fromArray(new Integer[] { 1, 2, 3 }).subscribe(System.out::println);
		Flux.empty().subscribe(System.out::println);
		Flux.range(1, 10).subscribe(System.out::println);
		Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
//		Flux.intervalMillis(1000).subscribe(System.out::println);
		
		Flux.generate(sink -> {
			sink.next("Hello");
			sink.complete();
		}).subscribe(System.out::println);
		final Random random = new Random();
		Flux.generate(ArrayList::new, (list, sink) -> {
			int value = random.nextInt(100);
			list.add(value);
			sink.next(value);
			if (list.size() == 10) {
				sink.complete();
			}
			return list;
		}).subscribe(System.out::println);

	}

}
