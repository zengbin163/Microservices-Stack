package com.chihuo.food.interfaces.facade.error;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.infrastructure.common.api.ResponsePages;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;

@RestController
@RequestMapping("/err")
public class ErrorApi {
	
	@Autowired
	private ResponsePages pages;
	
	@RequestMapping("/error")
	@NoLogin
	public void error(HttpServletResponse response) throws Exception {
		response.sendRedirect(pages.getPage(ResponsePages.ERROR));
	}
	
}
