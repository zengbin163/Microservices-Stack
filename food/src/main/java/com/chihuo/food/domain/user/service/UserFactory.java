package com.chihuo.food.domain.user.service;

import org.springframework.stereotype.Service;

import com.chihuo.food.domain.user.entity.User;
import com.chihuo.food.domain.user.entity.UserAccount;
import com.chihuo.food.domain.user.repository.po.UserAccountPO;
import com.chihuo.food.domain.user.repository.po.UserPO;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;

@Service
public class UserFactory {
	
	public User createUser(UserPO userPO) {
		if(null == userPO) {
			return null;
		}
		BeanCopier<User> beanCopier = BeanCopier.create(userPO, new User(), CopyOptions.create());
		return beanCopier.copy();
	}
	
	public UserPO createUserPO(User user) {
		if(null == user) {
			return null;
		}
		BeanCopier<UserPO> beanCopier = BeanCopier.create(user, new UserPO(), CopyOptions.create());
		return beanCopier.copy();
	}
	
	public UserAccount createUserAccount(UserAccountPO userAccountPO) {
		if(null == userAccountPO) {
			return null;
		}
		BeanCopier<UserAccount> beanCopier = BeanCopier.create(userAccountPO, new UserAccount(), CopyOptions.create());
		return beanCopier.copy();
	}
}
