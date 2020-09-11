package com.springcloud.feignclient.web.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import com.springcloud.feignclient.client.Client2;
import com.springcloud.feignclient.client.Client3;
import com.springcloud.feignclient.web.dao.UserDao;
import com.springcloud.feignclient.web.entity.User;

/**
 * @author liq
 * @Title: UserService
 * @ProjectName lcn-demo
 * @Description: TODO
 * @date 2019-06-0409:21
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private Client2 client2;
	@Autowired
	private Client3 client3;
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@LcnTransaction
	@Transactional(rollbackFor = Exception.class)
	public int insert(User user) {
		String transactionGroupId = TracingContext.tracing().groupId();
		log.error("---------client-1---------transactionGroupId : {}", transactionGroupId);
		Map map = (Map) JSON.parse(JSON.toJSONString(user));
		client2.getMethod("/user/insert", map);
		client3.getMethod("/user/insert", map);
		user.setGroupId(transactionGroupId);
		int ret = userDao.save(user);
		String s = null;
		s.length();
		return ret;
	}
}
