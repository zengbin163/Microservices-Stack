package com.chihuo.food.domain.user.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.user.repository.facade.UserRepository;
import com.chihuo.food.domain.user.repository.mapper.UserDao;
import com.chihuo.food.domain.user.repository.po.UserPO;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void save(UserPO userPO) {
		this.userDao.save(userPO);
	}

	@Override
	public List<UserPO> findUserList() {
		return this.userDao.findUserList();
	}

	@Override
	public List<UserPO> findUserListRandom(Integer userCount) {
		return this.userDao.findUserListRandom(userCount);
	}

}
