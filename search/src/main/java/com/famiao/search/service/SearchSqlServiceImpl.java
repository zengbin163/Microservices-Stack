package com.famiao.search.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.famiao.search.dao.SearchSqlDao;

/**
 * @desc
 * @author 曾斌
 * @version 创建时间：Jun 29, 2019 1:04:07 PM
 */
@Service
public class SearchSqlServiceImpl implements SearchSqlService {

    @Autowired
    private SearchSqlDao searchSqlDao;

    public Long countResultByPage(String sqlCount) {
        return this.searchSqlDao.executeCountDynamicSql(sqlCount);
    }

    @Override
    public IPage<Map<String, Object>> getResultByPage(String sql, Long currentPage, Long pageSize) {
        if (currentPage < 1) {
            currentPage = 1L;
        }
        Long pageIndex = (currentPage - 1) * pageSize;
        List<Map<String, Object>> list = this.searchSqlDao.executeDynamicSql(sql, pageIndex, pageSize);
        IPage<Map<String, Object>> page = new Page<Map<String, Object>>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        page.setRecords(list);
        return page;
    }
}
