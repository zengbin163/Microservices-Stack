package com.chihuo.coupon.infrastructure.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UidClientComponentFallback implements UidClientComponent {
	
	public static final String URL_PREFIX = "http://uid-service";
	
	private static final Logger log = LoggerFactory.getLogger(UidClientComponentFallback.class);
	
    @Autowired
    @Qualifier(value = "balanceTemplate")
    private RestTemplate template;

	@Override
	public Long getUID() {
		log.warn("@@@@@@@@@UidClientComponentFallback.getUID");
		String url = URL_PREFIX + "/uid/getUID";
		return template.getForObject(url, Long.class);
	}

	@Override
	public String parseUID(Long uid) {
		log.warn("@@@@@@@@@UidClientComponentFallback.parseUID");
		String url = URL_PREFIX + "/uid/parseUID?uid=" + uid;
        return template.getForObject(url, String.class);
	}

}
