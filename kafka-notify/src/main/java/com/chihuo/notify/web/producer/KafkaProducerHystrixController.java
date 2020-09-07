package com.chihuo.notify.web.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.notify.service.ProducerService;

@RestController
@RequestMapping("/producer-hystrix")
public class KafkaProducerHystrixController {
	
	@Autowired
	private ProducerService producerService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
    	this.producerService.send(key, value);
		return ResponseEntity.ok().build().getStatusCode().toString();
    }
	
}
