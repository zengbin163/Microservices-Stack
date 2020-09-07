package com.chihuo.food.interfaces.facade.user;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.application.service.UserApplicationService;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;

@RestController
@RequestMapping("/user")
public class UserApi {
    
	@Autowired
	private UserApplicationService userApplicationService;

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response createOrder(@RequestParam(value = "userId") Long userId, @RequestParam(value = "shoppingCat") String shoppingCat) throws Exception {
    	Map<Long, Integer> shoppingFoodList = JSONObject.parseObject(shoppingCat, Map.class);
    	BigDecimal payAmount = this.userApplicationService.createSingleOrder(userId, shoppingFoodList);
    	return Response.ok(payAmount);
    }
    
}
