package com.chihuo.api.component;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProducerComponent {

	@RequestMapping("/producer/send")
	public String send(@RequestParam("key") String key, @RequestParam("value") String value);

}
