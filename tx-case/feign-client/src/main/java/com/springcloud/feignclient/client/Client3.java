package com.springcloud.feignclient.client;

import com.springcloud.feignclient.fallback.Client2Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author liq
 * @Title: Client2
 * @ProjectName lcn-demo
 * @Description: TODO
 * @date 2019-06-0409:05
 */
@FeignClient(name = "client-3" , fallback = Client2Fallback.class)
public interface Client3 {

    @GetMapping("{method}")
    String getMethod(@PathVariable("method") String method, @RequestParam Map<String, Object> map);
}
