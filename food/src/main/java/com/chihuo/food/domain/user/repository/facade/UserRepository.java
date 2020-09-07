package com.chihuo.food.domain.user.repository.facade;

import java.util.List;

import com.chihuo.food.domain.user.repository.po.UserPO;

public interface UserRepository {

	void save(UserPO userPO);

	List<UserPO> findUserList();
	
	List<UserPO> findUserListRandom(Integer userCount);
	
}
