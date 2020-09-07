package com.famiao.search.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zengbin
 * @since 2019-06-18
 */
public interface SearchSqlDao {

    public List<Map<String, Object>> executeDynamicSql(@Param("value") String sql, @Param("pageIndex") Long pageIndex, @Param("pageSize") Long pageSize);
    
    public Long executeCountDynamicSql(@Param("value") String sql);

}
