package com.chihuo.food.domain.category.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.category.repository.facade.CategoryRepository;
import com.chihuo.food.domain.category.repository.mapper.CategoryDao;
import com.chihuo.food.domain.category.repository.po.CategoryPO;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryDao categoryDao;

	@Override
	public Integer save(CategoryPO categoryPO) {
		this.categoryDao.save(categoryPO);
		return categoryPO.getId();
	}

	@Override
	public void update(CategoryPO categoryPO) {
		this.categoryDao.update(categoryPO);
	}
	
    @Override
    public CategoryPO findById(Integer id) {
        return this.categoryDao.findById(id);
    }

	@Override
	public List<CategoryPO> queryCategoryListByParentId(Integer parentId) {
		return this.categoryDao.queryCategoryListByParentId(parentId);
	}

}
