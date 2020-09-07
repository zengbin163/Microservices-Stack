package com.chihuo.webflux.reactor;

import java.util.Arrays;
import java.util.List;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class ReactorSubscriber extends BaseSubscriber<Integer> {

	@Override
	protected void hookOnSubscribe(Subscription subscription) {
		System.out.println("ReactorSubscriber->hookOnSubscribe->Subscribe");
		subscription.request(Long.MAX_VALUE);
	}

	@Override
	protected void hookOnNext(Integer value) {
		if (value == 4) {
			// 背压，通知数据源，不要发送数据了
			System.out.println("背压->通知数据源->不要发送数据了");
			cancel();
		} else {
			System.out.println(value);
		}	
		request(Long.MAX_VALUE);
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Flux<Integer> flux = Flux.fromIterable(list);
        flux.subscribe(new ReactorSubscriber());
	}

}
