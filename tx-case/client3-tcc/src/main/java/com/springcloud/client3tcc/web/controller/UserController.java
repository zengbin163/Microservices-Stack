package com.springcloud.client3tcc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.client3tcc.web.entity.User;
import com.springcloud.client3tcc.web.service.UserService;

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
    public String insert(User user){
        String res ="success";
        userService.insert(user);
        return res;
    }

}
