package com.chihuo.api.component;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UidComponent {

	@RequestMapping("/uid/getUID")
	public Long getUID();

	@RequestMapping("/uid/parseUID")
	public String parseUID(@RequestParam("uid") Long uid);

}
