package com.chihuo.food.interfaces.facade.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.application.service.SellerApplicationService;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;

@RestController
@RequestMapping("/seller")
public class SellerApi {
    
    @Autowired
    private SellerApplicationService sellerApplicationService;
    
    @RequestMapping(value = "/createSellerData", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response createSellerData() {
        this.sellerApplicationService.createSellerData();
        return Response.ok();
    }

}
