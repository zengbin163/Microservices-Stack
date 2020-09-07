package com.chihuo.webflux.reactor.component.route;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.chihuo.webflux.reactor.component.handler.EmployeeHandler;
import com.chihuo.webflux.reactor.component.handler.TimeHandler;

@Configuration
public class RouterConfig {

    @Autowired
    private TimeHandler timeHandler;
    @Autowired
    private EmployeeHandler employeeHandler;

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(GET("/time"), req -> timeHandler.getTime(req))
        	   .andRoute(GET("/date"), timeHandler::getDate)
        	   .andRoute(GET("/times"), timeHandler::getTimes)
        	   .andRoute(GET("/hello"), timeHandler::hello)
        	   .andRoute(GET("/world"), timeHandler::world);  // 这种方式相对于上一行更加简洁
    }
    
    @Bean
    public RouterFunction<ServerResponse> reactorHandler() {
    	//路由函数编写
        return route(GET("/listEmployee"),employeeHandler::listEmployee)
               .andRoute(GET("/findEmployeeByName/{name}"),employeeHandler::findEmployeeByName)
               .andRoute(GET("/findEmployeeById/{id}"),employeeHandler::findEmployeeById)
               .andRoute(GET("/existById/{id}"),employeeHandler::existById)
               .andRoute(GET("/saveEmployee/{name}/{salary}"),employeeHandler::saveEmployee)
        	   .andRoute(GET("/updateEmployee/{id}/{name}/{salary}"),employeeHandler::updateEmployee);
    }

}
