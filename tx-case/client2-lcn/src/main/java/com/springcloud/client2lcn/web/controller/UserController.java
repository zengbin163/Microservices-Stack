package com.springcloud.client2lcn.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.client2lcn.web.entity.User;
import com.springcloud.client2lcn.web.service.UserService;


/**
 * @author liq
 * @Title: UserController
 * @ProjectName lcn-demo
 * @Description: TODO
 * @date 2019-06-0409:08
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/insert")
    public String update(User user){
        String res ="success";
        userService.insert(user);
        return res;
    }
    
}
