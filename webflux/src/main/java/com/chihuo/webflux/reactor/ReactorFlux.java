package com.chihuo.webflux.reactor;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import reactor.test.StepVerifier;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.webflux.reactor.valueobject.Employee;
import com.chihuo.webflux.reactor.valueobject.Leader;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactorFlux {
	
	private static final Logger log = LoggerFactory.getLogger(ReactorFlux.class);
	
	protected static final List<Employee> list = Arrays.asList (
			new Employee("1", "Alex", 1000),
			new Employee("2", "Michael", 2000), 
			new Employee("3", "Jack", 1500), 
			new Employee("4", "Owen", 1500),
			new Employee("5", "Denny", 2000)
	);

	public void createFlux() throws InterruptedException {
		
		// 整型
		Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5);
		integerFlux.subscribe(System.out::println);
		// 范围
		Flux<Integer> integerFlux1 = Flux.range(-1, 5);
		integerFlux1.subscribe(System.out::println);

		// 字符串
		Flux<String> stringFlux = Flux.just("hello", "world");
		stringFlux.subscribe(System.out::println);
		// 列表
		List<String> list = Arrays.asList("hello", "world");
		Flux<String> stringFlux1 = Flux.fromIterable(list);
		stringFlux1.subscribe(System.out::println);
		// Flux 创建
		Flux<String> stringFlux2 = Flux.from(stringFlux1);
		stringFlux2.subscribe(System.out::println);

		// 时间间隔
		Flux<Long> longFlux = Flux.interval(Duration.ofMillis(500));
		longFlux.subscribe(System.out::println);
		Thread.sleep(Long.MAX_VALUE);

	}
	
	public void subscribe1() {

		Flux<String> stringFlux = Flux.just("Hello", "World");
		stringFlux.subscribe(System.out::println);
		// 订阅方式一
		stringFlux.subscribe(val -> {
			log.info("val:{}", val);
		}, error -> {
			log.error("error:{}", error);
		}, () -> {
			log.info("Finished");
		}, subscription -> {
			Long maxValue = Long.MAX_VALUE;
			log.info("request maxValue:{}", maxValue);
			subscription.request(maxValue);
		});
		
	}
	
	public void subscribe2() {
		
		Flux<String> stringFlux = Flux.just("Hello", "World");
		stringFlux.subscribe(System.out::println);
		// 订阅方式二
		stringFlux.subscribe(new Subscriber<String>() {
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
	
    public void map() {
    	
        Flux<Employee> employeeFlux = Flux.fromIterable(list);
        List<Leader> leaderList = new ArrayList<Leader>();
        employeeFlux
                .filter(employee -> employee.getSalary() == 2000)
                .map(employee -> {
                    Leader leader = new Leader();
                    leader.setName(employee.getName());
                    leader.setSalary(employee.getSalary());
                    leaderList.add(leader);
                    return leader;
                 })
//                .log()
                .subscribe();
        
        Flux<Leader> leaderFlux = Flux.fromIterable(leaderList);
        leaderFlux.subscribe(new Subscriber<Leader>() {

			@Override
			public void onSubscribe(Subscription s) {
				Long maxValue = Long.MAX_VALUE;
				log.info("onSubscribe->{}", maxValue);
				s.request(maxValue);
			}

			@Override
			public void onNext(Leader t) {
				log.info("onNext->{}", JSONObject.toJSONString(t));
			}

			@Override
			public void onError(Throwable t) {
				log.error("onError->", t);
			}

			@Override
			public void onComplete() {
				log.info("onComplete->");
			}
        	
		});
        
        List<Leader> leaderList2 = new ArrayList<Leader>();

        employeeFlux
        		.map((in) -> {
		            Leader leader = new Leader();
		            leader.setName(in.getName());
		            leader.setSalary(in.getSalary());
		            leaderList2.add(leader);
		            return leader;
		         })
//        		.log()
        		.subscribe();
        
        Flux<Leader> leaderFlux2 = Flux.fromIterable(leaderList2);
        leaderFlux2.subscribe(new Subscriber<Leader>() {

			@Override
			public void onSubscribe(Subscription s) {
				Long maxValue = Long.MAX_VALUE;
				log.info("onSubscribe2->{}", maxValue);
				s.request(maxValue);
			}

			@Override
			public void onNext(Leader t) {
				log.info("onNext2->{}", JSONObject.toJSONString(t));
			}

			@Override
			public void onError(Throwable t) {
				log.error("onError2->", t);
			}

			@Override
			public void onComplete() {
				log.info("onComplete2->");
			}
        	
		});
        
    }
    
	public void error() {
		
		Flux.range(-2, 5).map(val -> {
			log.info("range->{}", val);
			if(val == 0) {
				throw new RuntimeException("val is zero");
			}
			return val;
		}).onErrorContinue((ex, val) -> { // 遇到错误继续订阅
			if (ex instanceof IOException) {
				log.error("ex:{},val:{}", ex, val);
			} else {
				log.error("other ex!", ex);
			}
		}).onErrorResume((ex) -> { // 遇到错误，返回新的Flux。继续订阅
			log.error("---------ex--->", ex);
			return Flux.range(-8, 4);
		}).subscribe(System.out::println);
		
	}

    public void flatMap() {
        Flux<String> stringFlux1 = Flux.just("a","b","c","d","e","f","g","h","i");
        //嵌套Flux
        Flux<Flux<String>> stringFlux2 = stringFlux1.window(2);
        stringFlux2
        		.flatMap(flux1 -> flux1.map(word -> word.toUpperCase()))
                .subscribe(System.out::println);
        //从嵌套Flux还原字符串Flux
        Flux<String> stringFlux3 = stringFlux2.flatMap(flux1 -> flux1);
        //stringFlux1 等于 stringFlux3
        stringFlux3.subscribe(System.out::println);
    }
    
    public void concatMap() throws InterruptedException {
        Flux<String> stringFlux1 = Flux.just("a","b","c","d","e","f","g","h","i");
        Flux<Flux<String>> stringFlux2 = stringFlux1.window(2);
        stringFlux2.concatMap(flux1 ->flux1.map(word ->word.toUpperCase())
                .delayElements(Duration.ofMillis(200)))
                .subscribe(x -> System.out.print("->"+x));
        Thread.sleep(2000);
    }
    
    public void collect(){
        Flux<Integer> integerFlux = Flux.range(1,5);
        //转换成以List<Integer>为对象的Mono
        Mono<List<Integer>> mono = integerFlux.collectList();
        mono.subscribe(System.out::println);
        Flux<Employee> employeeFlux = Flux.fromIterable(list);
        //转换成以List<Employee>为对象的Mono
        Mono<List<Employee>> mono1 = employeeFlux.collectSortedList(Comparator.comparing(Employee::getSalary));
        mono1.subscribe(System.out::println);
        //转换成以Map<String,Employee>为对象的Mono
        Mono<Map<String,Employee>> mono2 = employeeFlux.collectMap(item ->item.getName(), item ->item);
        mono2.subscribe(System.out::println);
    }
    
    public void collectMap(){
        Flux<Employee> employeeFlux = Flux.fromIterable(list);
        //转换HashMap
        Mono<Map<String,Employee>> mono = employeeFlux.collectMap(key ->key.getName(),val ->val);
        mono.subscribe(System.out::println);
    }
    
    public void take(){
        //根据数量获取
        Flux.range(1,10).take(0).log().subscribe(System.out::println);
        //根据实际获取
        Flux.range(1,10000).take(Duration.ofMillis(2)).log().subscribe(System.out::println);
        //根据条件获取
        Flux.range(1,10).takeUntil(item ->item == 5).log().subscribe(System.out::println);
    }
    
    public void window(){
        //一维Flux
        Flux<String> stringFlux1 = Flux.just("a","b","c","d","e","f","g","h","i");
        //二维Flux
        Flux<Flux<String>> stringFlux2 = stringFlux1.window(2);
        stringFlux2.count().subscribe(System.out::println);
        //三维Flux
        Flux<Flux<Flux<String>>> stringFlux3 = stringFlux2.window(3);
        stringFlux3.count().subscribe(System.out::println);
    }
    
	public void verify() {
		
		StepVerifier.create(Flux.just("foo", "bar"))
				    .expectNext("foo")
				    .expectNext("bar")
				    .expectComplete()
				    .verify();

        Flux<Integer> integerFlux = Flux.range(1,5);
		integerFlux.subscribe(x -> System.out.print("->" + x));

		StepVerifier.create(integerFlux)
			        .expectNext(1)
			        .expectNext(2)
			        .expectNext(3,4,5)
			        .expectComplete()
			        .verify();

	    StepVerifier.create(Flux.just("flux", "mono").flatMap(s -> Flux.fromArray(s.split("\\s*")).delayElements(Duration.ofMillis(1000))).doOnNext(System.out::print))
	            	.expectNextCount(8)
	            	.verifyComplete();
	    
	}
	
	public void thread() throws Exception {
		Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);
		flux
			.map(i -> {
				System.out.println(Thread.currentThread().getName() + "-map1");
				return i * 3;
			})
			.publishOn(Schedulers.elastic())
			.map(i -> {
				System.out.println(Thread.currentThread().getName() + "-map2");
				return i / 3;
			})
			.subscribeOn(Schedulers.parallel())
			.subscribe(i -> System.out.println(Thread.currentThread().getName() + "-" + i));

		Thread.sleep(10000);
	}
	
	public static void main(String[] args) throws Exception {
		ReactorFlux reactorFlux = new ReactorFlux();
		//reactorFlux.createFlux();
		//reactorFlux.subscribe1();
		//reactorFlux.subscribe2();
		//reactorFlux.map();
		//reactorFlux.error();
//reactorFlux.flatMap();
		//reactorFlux.concatMap();
		//reactorFlux.collect();
		//reactorFlux.collectMap();
		//reactorFlux.take();
		//reactorFlux.window();
		//reactorFlux.verify();
		reactorFlux.thread();
		
	}
	

	
}
