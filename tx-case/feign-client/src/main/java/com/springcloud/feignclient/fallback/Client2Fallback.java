package com.springcloud.feignclient.fallback;

import com.alibaba.fastjson.JSON;
import com.springcloud.feignclient.client.Client2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liq
 * @Title: Client2Fallback
 * @ProjectName lcn-demo
 * @Description: TODO
 * @date 2019-06-0409:05
 */
public class Client2Fallback implements Client2 {
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String getMethod(String method , Map<String, Object> requestEntity) {
		Map result = new HashMap();
        result.put(30041,"网络延迟，请稍后再试");
        return JSON.toJSONString(result);
    }



}
