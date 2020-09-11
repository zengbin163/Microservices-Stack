package com.springcloud.client3tcc.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import com.springcloud.client3tcc.web.dao.UserDao;
import com.springcloud.client3tcc.web.entity.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liq
 * @Title: UserService
 * @ProjectName lcn-demo
 * @Description: TODO
 * @date 2019-06-0409:21
 */
@Service
@Slf4j
public class UserService {
	@Autowired
	private UserDao userDao;

	@TxcTransaction(propagation = DTXPropagation.SUPPORTS)
	@Transactional(rollbackFor = Exception.class)
	public int insert(User user) {
        //user.setGroupId(TracingContext.tracing().groupId());
		boolean hasGroup = TracingContext.tracing().hasGroup();
		if(hasGroup) {
			log.error("---------client-3---------transactionGroupId : {}", TracingContext.tracing().groupId());
		} else {
			log.error("---------client-3---------no transaction group");
		}
		
//	    int i = 100/0;
		
		return userDao.save(user);
	}

	public void confirmInsert(User user) {
		log.info("成功");
	}

	public void cancelInsert(User user) {
		log.error("失败,正在回退");
		userDao.delete(TracingContext.tracing().groupId());
		log.error("回退成功");
	}

}
