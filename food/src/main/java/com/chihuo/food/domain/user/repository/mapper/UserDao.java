package com.chihuo.food.domain.user.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.user.repository.po.UserPO;

public interface UserDao extends BaseMapper<UserPO> {

	void save(@Param("userPO") UserPO userPO);
	
	List<UserPO> findUserList();

	List<UserPO> findUserListRandom(@Param("userCount") Integer userCount);

}
