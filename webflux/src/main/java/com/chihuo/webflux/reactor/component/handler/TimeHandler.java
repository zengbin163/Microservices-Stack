package com.chihuo.webflux.reactor.component.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TimeHandler {
	
	public Mono<ServerResponse> hello(ServerRequest request) {
		return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("hello"), String.class);
	}

	public Mono<ServerResponse> world(ServerRequest request) {
		return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("world"), String.class);
	}

    public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }
    
    public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
    }
    
	public Mono<ServerResponse> getTimes(ServerRequest request) {
		// 每隔一秒发送当前的时间
		return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Flux.interval(Duration.ofSeconds(1)).map(it -> new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
	}

    
}
