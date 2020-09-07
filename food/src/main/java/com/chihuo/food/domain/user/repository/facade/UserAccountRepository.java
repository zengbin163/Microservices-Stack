package com.chihuo.food.domain.user.repository.facade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.chihuo.food.domain.user.repository.po.UserAccountPO;

public interface UserAccountRepository {

	void save(UserAccountPO userAccountPO);

	void updateHongbao(Long userId, BigDecimal hongbaoTotal);
	
	Optional<UserAccountPO> findUserAccountByUserId(Long userId);
	
	List<UserAccountPO> findUserAccountList();
}
