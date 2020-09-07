package com.chihuo.food.interfaces.facade.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.application.service.OrderApplicationService;
import com.chihuo.food.infrastructure.common.api.Response;

@RestController
@RequestMapping("/order")
public class OrderApi {
    
    @Autowired
    private OrderApplicationService orderApplicationService;
    
    @RequestMapping(value = "/findOrderList", method = RequestMethod.POST)
    @ResponseBody
    public Response findOrderList(@RequestParam(value = "current") Integer current, @RequestParam(value = "size") Integer size) {
    	return Response.ok(this.orderApplicationService.findOrderList(current, size));
    }
    
    @RequestMapping(value = "/findOrderCount", method = RequestMethod.POST)
    @ResponseBody
    public Response findOrderCount() {
    	return Response.ok(this.orderApplicationService.findOrderCount());
    }

    @RequestMapping(value = "/findOrderItemList", method = RequestMethod.POST)
    @ResponseBody
    public Response findOrderItemList(@RequestParam(value = "current") Integer current, @RequestParam(value = "size") Integer size) {
    	return Response.ok(this.orderApplicationService.findOrderItemList(current, size));
    }
    
    @RequestMapping(value = "/findOrderItemCount", method = RequestMethod.POST)
    @ResponseBody
    public Response findOrderItemCount() {
    	return Response.ok(this.orderApplicationService.findOrderItemCount());
    }
    
    @RequestMapping(value = "/findUserOrderList", method = RequestMethod.POST)
    @ResponseBody
    public Response findUserOrderList(@RequestParam(value = "current") Integer current, @RequestParam(value = "size") Integer size, @RequestParam(value = "userId") Long userId) {
    	return Response.ok(this.orderApplicationService.findUserOrderList(current, size, userId));
    }

    @RequestMapping(value = "/refreshOrder", method = RequestMethod.POST)
    @ResponseBody
    public Response refreshOrder() {
    	this.orderApplicationService.refreshOrder();
    	return Response.ok();
    }
    
}
