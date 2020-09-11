package com.springcloud.client2lcn.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import com.springcloud.client2lcn.web.dao.UserDao;
import com.springcloud.client2lcn.web.entity.User;

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

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@TxcTransaction(propagation = DTXPropagation.SUPPORTS)
	@Transactional(rollbackFor = Exception.class)
	public int insert(User user) {
		boolean hasGroup = TracingContext.tracing().hasGroup();
		if(hasGroup) {
			log.error("---------client-2---------transactionGroupId : {}", TracingContext.tracing().groupId());
		} else {
			log.error("---------client-2---------no transaction group");
		}
        //user.setGroupId(TracingContext.tracing().groupId());
		return userDao.save(user);
	}
}
