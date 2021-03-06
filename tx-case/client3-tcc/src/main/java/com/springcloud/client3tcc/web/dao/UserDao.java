package com.springcloud.client3tcc.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springcloud.client3tcc.web.entity.User;

/**
 * @author liq
 * @Title: UserDao
 * @ProjectName lcn-demo
 * @Description: TODO
 * @date 2019-06-0409:15
 */
@Mapper
public interface UserDao {

    public int save(@Param("user") User user);
    public int delete(@Param("groupId") String groupId);

}
