package com.springcloud.feignclient.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.feignclient.web.entity.User;
import com.springcloud.feignclient.web.service.UserService;

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
	public String update(
			@RequestParam(value = "id") Integer id, 
			@RequestParam(value = "name") String name,
			@RequestParam(value = "username") String username, 
			@RequestParam(value = "password") String password) {
		User user = new User(id, name, username, password);
		userService.insert(user);
		return "success";
	}
	
}
