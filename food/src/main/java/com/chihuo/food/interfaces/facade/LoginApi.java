package com.chihuo.food.interfaces.facade;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.infrastructure.common.session.SessionToolComponent;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;
import com.chihuo.food.infrastructure.util.IdGenerator;

@RestController
@RequestMapping("/login")
public class LoginApi {
    
    @Autowired
    private SessionToolComponent sessionToolComponent;

    @RequestMapping(value = "/do", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
    public Response doLogin(HttpServletRequest request, @RequestParam(value = "mobile") String mobile, @RequestParam(value = "password") String password) {
    	String userId = IdGenerator.nextId();
    	String token = this.sessionToolComponent.jwt(request, userId, mobile);
    	JSONObject json = new JSONObject();
    	json.put(SessionToolComponent.TOKEN, token);
    	return Response.ok(json);
    }

}
