package com.chihuo.food.domain.category.repository.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.category.repository.po.CategoryTypePO;

public interface CategoryTypeDao extends BaseMapper<CategoryTypePO> {
	
    List<CategoryTypePO> queryCategoryTypeList();

}
