package com.chihuo.food.domain.category.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.category.repository.facade.CategoryTypeRepository;
import com.chihuo.food.domain.category.repository.mapper.CategoryTypeDao;
import com.chihuo.food.domain.category.repository.po.CategoryTypePO;

@Repository
public class CategoryTypeRepositoryImpl implements CategoryTypeRepository {

    @Autowired
    private CategoryTypeDao categoryTypeDao;

	@Override
	public List<CategoryTypePO> queryCategoryTypeList() {
		return this.categoryTypeDao.queryCategoryTypeList();
	}

}
