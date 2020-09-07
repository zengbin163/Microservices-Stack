package com.chihuo.food.infrastructure.consumer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CouponClientComponentFallback implements CouponClientComponent {

	public static final String URL_PREFIX = "http://coupon-service";
	
	private static final Logger log = LoggerFactory.getLogger(CouponClientComponentFallback.class);
	
    @Autowired
    @Qualifier(value = "balanceTemplate")
    private RestTemplate template;

	@Override
	public Long save(String json) {
		log.warn("@@@@@@@@@CouponClientComponentFallback.save");
		String url = URL_PREFIX + "/coupon/save";
		return template.getForObject(url, Long.class, json);
	}

	@Override
	public boolean update(String json) {
		log.warn("@@@@@@@@@CouponClientComponentFallback.update");
		String url = URL_PREFIX + "/coupon/update";
		return template.getForObject(url, Boolean.class, json);
	}

	@Override
	public boolean remove(Long uid) {
		log.warn("@@@@@@@@@CouponClientComponentFallback.remove");
		String url = URL_PREFIX + "/coupon/remove";
		return template.getForObject(url, Boolean.class, uid);
	}

	@Override
	public String findUserCouponByUid(Long uid) {
		log.warn("@@@@@@@@@CouponClientComponentFallback.findUserCouponByUid");
		String url = URL_PREFIX + "/coupon/findUserCouponByUid";
		return template.getForObject(url, String.class, uid);
	}

	@Override
	public boolean use(Long uid) {
		log.warn("@@@@@@@@@CouponClientComponentFallback.use");
		String url = URL_PREFIX + "/coupon/use";
		return template.getForObject(url, Boolean.class, uid);
	}

	@Override
	public String findCouponListByCategoryIds(List<Long> ids) {
		log.warn("@@@@@@@@@CouponClientComponentFallback.findCouponListByCategoryIds");
		String url = URL_PREFIX + "/coupon/findCouponListByCategoryIds";
		return template.getForObject(url, String.class, ids);
	}

}
