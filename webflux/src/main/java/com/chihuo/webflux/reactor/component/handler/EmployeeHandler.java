package com.chihuo.webflux.reactor.component.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.chihuo.webflux.reactor.repository.EmployeeRepository;
import com.chihuo.webflux.reactor.valueobject.Employee;

import reactor.core.publisher.Mono;

@Component
public class EmployeeHandler {
	
    @Autowired
    private EmployeeRepository employeeRepository;

	public Mono<ServerResponse> listEmployee(ServerRequest request) {
	    return ok().contentType(MediaType.APPLICATION_JSON).body(employeeRepository.findAll(), Employee.class);
	}

    public Mono<ServerResponse> saveEmployee(ServerRequest request) {
        String name = request.pathVariable("name");
        Double salary = Double.valueOf(request.pathVariable("salary"));
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);
        Mono<Employee> mono = Mono.just(employee);
        return ok().build(this.employeeRepository.insert(mono).then());
    }
    
    public Mono<ServerResponse> updateEmployee(ServerRequest request) {
    	String id = request.pathVariable("id");
    	String name = request.pathVariable("name");
        Double salary = Double.valueOf(request.pathVariable("salary"));
    	Employee employee = new Employee();
    	employee.setId(id);
    	employee.setName(name);
    	employee.setSalary(salary);
    	Mono<Employee> mono = this.employeeRepository.save(employee);
    	return ok().build(mono.then());
    }
    
    public Mono<ServerResponse> findEmployeeByName(ServerRequest request) {
    	String name = request.pathVariable("name");
    	Mono<Employee> mono = this.employeeRepository.findEmployeeByName(name);
    	return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Employee.class);
    }
    
    public Mono<ServerResponse> findEmployeeById(ServerRequest request) {
    	String id = request.pathVariable("id");
    	/**
    	Employee employee = new Employee();
    	employee.setId(id);
    	Example<Employee> example = Example.of(employee);
    	Mono<Employee> mono = this.employeeRepository.findOne(example);
    	**/
    	Mono<String> idMono = Mono.just(id);
    	Mono<Employee> mono = this.employeeRepository.findById(idMono);
    	return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Employee.class);
    }
    
    public Mono<ServerResponse> existById(ServerRequest request) {
    	String id = request.pathVariable("id");
    	Mono<String> idMono = Mono.just(id);
    	Mono<Boolean> mono = this.employeeRepository.existsById(idMono);
    	return ok().contentType(MediaType.APPLICATION_JSON).body(mono, Employee.class);
    }

}
