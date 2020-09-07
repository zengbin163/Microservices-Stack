package com.chihuo.food.domain.user.repository.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.user.repository.po.UserAccountPO;

public interface UserAccountDao extends BaseMapper<UserAccountPO> {

	void save(@Param("userAccountPO") UserAccountPO userAccountPO);
	
	void updateHongbao(@Param("userId") Long userId, @Param("hongbaoTotal") BigDecimal hongbaoTotal);
	
	Optional<UserAccountPO> findUserAccountByUserId(@Param("userId") Long userId);

	List<UserAccountPO> findUserAccountList();
}
