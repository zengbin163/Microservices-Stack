package com.chihuo.food.interfaces.facade.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.application.operation.CacheOpsApplicationOperation;
import com.chihuo.food.application.operation.DataApplicationOperation;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;

@RestController
@RequestMapping("/userData")
public class UserDataApi {
    
    @Autowired
    private DataApplicationOperation dataApplicationOperation;
    @Autowired
    private CacheOpsApplicationOperation cacheOpsApplicationOperation;

    @RequestMapping(value = "/createBatchOrder", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response createBatchOrder(@RequestParam(value = "userCount") Integer userCount, @RequestParam(value = "foodCount") Integer foodCount) throws Exception {
    	this.dataApplicationOperation.createBatchOrder(userCount, foodCount);
    	return Response.ok();
    }

    @RequestMapping(value = "/sendUserCoupon", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response sendUserCoupon() {
    	this.dataApplicationOperation.sendUserCoupon();
    	return Response.ok();
    }

    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response createBatchOrder(@RequestParam(value = "keysPattern") String keysPattern) throws Exception {
    	this.cacheOpsApplicationOperation.clear(keysPattern);
    	return Response.ok();
    }
    
}
