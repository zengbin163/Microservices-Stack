package com.chihuo.food.domain.category.repository.facade;

import java.util.List;

import com.chihuo.food.domain.category.repository.po.CategoryTypePO;

public interface CategoryTypeRepository {

    List<CategoryTypePO> queryCategoryTypeList();

}