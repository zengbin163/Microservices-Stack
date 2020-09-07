package com.chihuo.webflux.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chihuo.webflux.reactor.valueobject.Employee;

import reactor.core.publisher.Mono;

@RestController
public class WebFluxController {
	
	WebClient webClient = WebClient.create("http://localhost:7788");

	@GetMapping("/webflux")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("webflux-ok");
	}
	
	@GetMapping("/mono")
    public Mono<String> mono(){
        return Mono.just("mono");
    }
	
	@GetMapping("/findByName")
	public Mono<Employee> findByName(@RequestParam(value = "name") String name) {
	    return webClient
	            .get()
	            .uri("/findEmployeeByName/" + name)
	            .accept(MediaType.APPLICATION_JSON)
	            .exchange()
	            .flatMap(cr -> cr.bodyToMono(Employee.class));
	}
	
	@GetMapping("/findById")
	public Mono<Employee> findById(@RequestParam(value = "id") String id) {
		return webClient
				.get()
				.uri("/findEmployeeById/" + id)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(cr -> cr.bodyToMono(Employee.class));
	}

	
}
