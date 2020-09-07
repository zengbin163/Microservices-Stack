package com.chihuo.webflux.reactor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorMono {
	
	private static final Logger log = LoggerFactory.getLogger(ReactorFlux.class);

	public void createMono() {
		
		// 字符串
		Mono<String> mono1 = Mono.just("mono1->Hello World");
		mono1.subscribe(System.out::println);

		// Callable创建
		Mono<String> mono2 = Mono.fromCallable(() -> {
			return "mono2->Hello World";
		});
		mono2.subscribe(System.out::println);

		// Future创建
		Mono<String> mono3 = Mono.fromFuture(CompletableFuture.completedFuture("mono3->Hello World"));
		mono3.subscribe(System.out::println);

		Random random = new Random();
		// Suppier创建
		Mono<Double> doubleMono1 = Mono.fromSupplier(random::nextDouble);
		doubleMono1.subscribe(System.out::println);

		// Mono创建
		Mono<Double> doubleMono2 = Mono.from(doubleMono1);
		doubleMono2.subscribe(System.out::println);

		// Flux创建
		Mono<Integer> integerMono = Mono.from(Flux.range(1, 5));
		integerMono.subscribe(System.out::println);
		
	}

	public void subscribe1() {

		Mono<String> stringMono = Mono.just("Hello World");
		
		stringMono.subscribe(System.out::println);
		// 订阅方式一
		stringMono.subscribe(val -> {
			log.info("val:{}", val);
		}, error -> {
			log.error("error:{}", error);
		}, () -> {
			log.info("onComplete");
		}, subscription -> {
			subscription.request(1);
		});
		
	}
	
	public void subscribe2() {
		
		Mono<String> stringMono = Mono.just("Hello World");
		stringMono.subscribe(System.out::println);
		// 订阅方式二
		stringMono.subscribe(new Subscriber<String>() {
			@Override
			public void onSubscribe(Subscription subscription) {
				Long maxValue = Long.MAX_VALUE;
				log.info("request maxValue:{}", maxValue);
				subscription.request(maxValue);
			}
			
			@Override
			public void onNext(String s) {
				log.info("onNext:{}", s);
			}
			
			@Override
			public void onError(Throwable throwable) {
				log.error("Error Message from subscribe", throwable);
			}
			
			@Override
			public void onComplete() {
				log.info("onComplete");
			}
		});
		
	}
	
	public static void main(String[] args) {
		ReactorMono reactorMono = new ReactorMono();
		reactorMono.subscribe2();
	}
	
}
