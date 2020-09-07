package com.famiao.search.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.famiao.search.vo.entity.User;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zengbin
 * @since 2019-06-18
 */
public interface UserDao extends BaseMapper<User> {
    IPage<User> getUserForTestMapperLocation(Page<?> page);

    User getUserByUUId(String uuid);
}
