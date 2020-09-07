package com.chihuo.webflux.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chihuo.webflux.mail.Mail;

@RestController
@RequestMapping("/mail")
public class MailController {

	private Mail mail = new Mail();
	
	@GetMapping("/send")
	public ResponseEntity<String> send(@RequestParam(value = "sendFrom") String sendFrom, @RequestParam(value = "sendTo") String sendTo) {
		JSONArray array = JSONArray.parseArray(sendTo);
		try {
			mail.sendGroupMail(sendFrom, array);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok("邮件发送成功!");
	}
	
	public static void main(String[] args) {
		String sendMail = "[{\"email\":\"532799609@qq.com\",\"title\":\"来自法苗的问候\",\"content\":\"你好，来自法苗网的问候\"},{\"email\":\"641866658@qq.com\",\"title\":\"来自法苗的问候\",\"content\":\"你好，来自法苗网的问候\"},{\"email\":\"zengbin@famiaowang.com\",\"title\":\"来自法苗的问候\",\"content\":\"你好，来自法苗网的问候\"}]";
		JSONArray array = JSONArray.parseArray(sendMail);
		for (int i = 0; i < array.size(); i++) {
			JSONObject json = array.getJSONObject(i);
			System.out.println(json.getString("email"));
			System.out.println(json.getString("title"));
			System.out.println(json.getString("content"));
		}
	}

}
