package com.famiao.search.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.famiao.search.vo.entity.User;

public interface UserService {
    IPage<User> getUserForTestMapperLocation(Page<User> page);

    User getUserByUUId(String uuid);
}
