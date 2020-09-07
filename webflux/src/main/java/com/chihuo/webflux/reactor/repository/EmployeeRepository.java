package com.chihuo.webflux.reactor.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.chihuo.webflux.reactor.valueobject.Employee;

import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
	
	Mono<Employee> findEmployeeByName(String name);

}
