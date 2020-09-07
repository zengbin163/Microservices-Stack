package com.famiao.search.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @desc
 * @author zengbin
 * @version 创建时间：Jun 29, 2019 11:02:56 AM
 */
public interface SearchSqlService {
    
    public Long countResultByPage(String sqlCount);

    public IPage<Map<String, Object>> getResultByPage(String sql, Long currentPage, Long pageSize);
}
