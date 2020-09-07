package com.chihuo.food.domain.category.repository.facade;

import java.util.List;

import com.chihuo.food.domain.category.repository.po.CategoryPO;

public interface CategoryRepository {

	Integer save(CategoryPO categoryPO);

    void update(CategoryPO categoryPO);
    
	CategoryPO findById(Integer id);

    List<CategoryPO> queryCategoryListByParentId(Integer parentId);

}