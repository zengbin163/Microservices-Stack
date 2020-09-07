package com.chihuo.food.interfaces.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DingdingApi {
    
    @Value("${member.notes}")
    private String memberNotes;
    
    @RequestMapping(value = "/dingding", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name", defaultValue = "zhangsan") String name) {
        return String.format("你好 %s! %s", name, memberNotes);
    }

}
