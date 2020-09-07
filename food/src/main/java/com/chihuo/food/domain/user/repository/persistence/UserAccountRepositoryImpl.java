package com.chihuo.food.domain.user.repository.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.user.repository.facade.UserAccountRepository;
import com.chihuo.food.domain.user.repository.mapper.UserAccountDao;
import com.chihuo.food.domain.user.repository.po.UserAccountPO;

@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {

	@Autowired
	private UserAccountDao userAccountDao;
	
	@Override
	public void save(UserAccountPO userAccountPO) {
		this.userAccountDao.save(userAccountPO);
	}
	
	@Override
	public void updateHongbao(Long userId, BigDecimal hongbaoTotal) {
		this.userAccountDao.updateHongbao(userId, hongbaoTotal);
	}

	@Override
	public Optional<UserAccountPO> findUserAccountByUserId(Long userId) {
		return this.userAccountDao.findUserAccountByUserId(userId);
	}

	@Override
	public List<UserAccountPO> findUserAccountList() {
		return this.userAccountDao.findUserAccountList();
	}

}
