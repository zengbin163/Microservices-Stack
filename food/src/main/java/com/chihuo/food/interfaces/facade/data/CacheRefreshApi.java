package com.chihuo.food.interfaces.facade.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.application.operation.CacheOpsApplicationOperation;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;

@RestController
@RequestMapping("/cacheRefresh")
public class CacheRefreshApi {
    
    @Autowired
    private CacheOpsApplicationOperation cacheOpsApplicationService;

    @RequestMapping(value = "/refreshFood", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response refreshFood() {
    	this.cacheOpsApplicationService.refreshFood();
    	return Response.ok();
    }
    
    @RequestMapping(value = "/refreshFoodStock", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response refreshFoodStock() {
    	this.cacheOpsApplicationService.refreshFoodStock();
    	return Response.ok();
    }
    
    @RequestMapping(value = "/refreshUser", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response refreshUser() {
    	this.cacheOpsApplicationService.refreshUser();
    	return Response.ok();
    }
    
    @RequestMapping(value = "/refreshUserAccount", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response refreshUserAccount() {
    	this.cacheOpsApplicationService.refreshUserAccount();
    	return Response.ok();
    }
    
    @RequestMapping(value = "/refreshSeller", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response refreshSeller() {
    	this.cacheOpsApplicationService.refreshSeller();
    	return Response.ok();
    }
    
    @RequestMapping(value = "/refreshSellerFood", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response refreshSellerFood() {
    	this.cacheOpsApplicationService.refreshSellerFood();
    	return Response.ok();
    }
    
    @RequestMapping(value = "/refreshPromotion", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response refreshPromotion() {
    	this.cacheOpsApplicationService.refreshPromotion();
    	return Response.ok();
    }
    
}
