package com.chihuo.food.infrastructure.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderItemClientComponentFallback implements OrderItemClientComponent {

	public static final String URL_PREFIX = "http://order-service";
	
	private static final Logger log = LoggerFactory.getLogger(OrderItemClientComponentFallback.class);
	
    @Autowired
    @Qualifier(value = "balanceTemplate")
    private RestTemplate template;

	@Override
	public Long save(String json) {
		log.warn("@@@@@@@@@OrderItemClientComponentFallback.save");
		String url = URL_PREFIX + "/orderItem/save";
		return template.getForObject(url, Long.class, json);
	}

	@Override
	public String findOrderItemList(Integer current, Integer size) {
		log.warn("@@@@@@@@@OrderItemClientComponentFallback.findOrderItemList");
		String url = URL_PREFIX + "/orderItem/findOrderItemList";
		return template.getForObject(url, String.class, current, size);
	}

	@Override
	public Integer findOrderItemCount() {
		log.warn("@@@@@@@@@OrderItemClientComponentFallback.findOrderItemCount");
		String url = URL_PREFIX + "/orderItem/findOrderItemCount";
		return template.getForObject(url, Integer.class);
	}

	@Override
	public Integer updateOrderItemBatch(String json) {
		log.warn("@@@@@@@@@OrderItemClientComponentFallback.updateOrderItemBatch");
		String url = URL_PREFIX + "/orderItem/updateOrderItemBatch";
		return template.getForObject(url, Integer.class, json);
	}

}
