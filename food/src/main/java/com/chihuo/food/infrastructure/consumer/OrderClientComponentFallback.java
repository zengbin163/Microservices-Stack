package com.chihuo.food.infrastructure.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClientComponentFallback implements OrderClientComponent {

	public static final String URL_PREFIX = "http://order-service";
	
	private static final Logger log = LoggerFactory.getLogger(OrderClientComponentFallback.class);
	
    @Autowired
    @Qualifier(value = "balanceTemplate")
    private RestTemplate template;

	@Override
	public Long save(String json) {
		log.warn("@@@@@@@@@OrderClientComponentFallback.save");
		String url = URL_PREFIX + "/order/save";
		return template.getForObject(url, Long.class, json);
	}

	@Override
	public String findOrderList(Integer current, Integer size) {
		log.warn("@@@@@@@@@OrderClientComponentFallback.findOrderList");
		String url = URL_PREFIX + "/order/findOrderList";
		return template.getForObject(url, String.class, current, size);
	}

	@Override
	public Integer findOrderCount() {
		log.warn("@@@@@@@@@OrderClientComponentFallback.findOrderCount");
		String url = URL_PREFIX + "/order/findOrderCount";
		return template.getForObject(url, Integer.class);
	}

	@Override
	public String findUserOrderList(Integer current, Integer size, Long userId) {
		log.warn("@@@@@@@@@OrderClientComponentFallback.findUserOrderList");
		String url = URL_PREFIX + "/order/findUserOrderList";
		return template.getForObject(url, String.class, current, size, userId);
	}

	@Override
	public String findSellerOrderList(Integer current, Integer size, Long sellerId) {
		log.warn("@@@@@@@@@OrderClientComponentFallback.findSellerOrderList");
		String url = URL_PREFIX + "/order/findSellerOrderList";
		return template.getForObject(url, String.class, current, size, sellerId);
	}

	@Override
	public String findOrderById(Long uid) {
		log.warn("@@@@@@@@@OrderClientComponentFallback.findOrderById");
		String url = URL_PREFIX + "/order/findOrderById";
		return template.getForObject(url, String.class, uid);
	}

	@Override
	public String refreshSellerIdOrderList(Long userId) {
		log.warn("@@@@@@@@@OrderClientComponentFallback.refreshSellerIdOrderList");
		String url = URL_PREFIX + "/order/refreshSellerIdOrderList";
		return template.getForObject(url, String.class, userId);
	}

	@Override
	public Integer countRefreshSellerIdOrderList(Long userId) {
		log.warn("@@@@@@@@@OrderClientComponentFallback.countRefreshSellerIdOrderList");
		String url = URL_PREFIX + "/order/countRefreshSellerIdOrderList";
		return template.getForObject(url, Integer.class, userId);
	}

	@Override
	public Integer updateOrderBatch(String json) {
		log.warn("@@@@@@@@@OrderClientComponentFallback.updateOrderBatch");
		String url = URL_PREFIX + "/order/updateOrderBatch";
		return template.getForObject(url, Integer.class, json);
	}

}
