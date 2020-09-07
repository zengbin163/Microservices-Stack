package com.chihuo.notify.web.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.api.component.ProducerComponent;
import com.chihuo.notify.service.ProducerService;

@RestController
public class KafkaProducerController implements ProducerComponent {
	
	@Autowired
	private ProducerService producerService;

	@Override
    public String send(String key, String value) {
    	this.producerService.send(key, value);
		return ResponseEntity.ok().build().getStatusCode().toString();
    }
	
}
