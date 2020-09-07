package com.chihuo.food.domain.category.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.category.repository.po.CategoryPO;

public interface CategoryDao extends BaseMapper<CategoryPO> {
	
	void save(@Param("categoryPO") CategoryPO categoryPO);
	
	void update(@Param("categoryPO") CategoryPO categoryPO);
	
	CategoryPO findById(Integer id);

    List<CategoryPO> queryCategoryListByParentId(Integer parentId);

}
