package com.famiao.search.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.famiao.search.dao.UserDao;
import com.famiao.search.service.user.UserService;
import com.famiao.search.vo.entity.User;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public IPage<User> getUserForTestMapperLocation(Page<User> page) {
        return this.userDao.getUserForTestMapperLocation(page);
    }

    @Override
    public User getUserByUUId(String uuid) {
        return this.userDao.getUserByUUId(uuid);
    }

}
