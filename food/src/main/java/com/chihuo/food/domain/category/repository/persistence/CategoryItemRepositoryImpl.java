package com.chihuo.food.domain.category.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.category.repository.facade.CategoryItemRepository;
import com.chihuo.food.domain.category.repository.mapper.CategoryItemDao;
import com.chihuo.food.domain.category.repository.po.CategoryItemPO;

@Repository
public class CategoryItemRepositoryImpl implements CategoryItemRepository {

    @Autowired
    private CategoryItemDao categoryItemDao;

	@Override
	public Integer save(CategoryItemPO categoryItemPO) {
		this.categoryItemDao.save(categoryItemPO);
		return categoryItemPO.getId();
	}

	@Override
	public void update(CategoryItemPO categoryItemPO) {
		this.categoryItemDao.update(categoryItemPO);
	}
	
	@Override
	public void delete(Integer id) {
		this.categoryItemDao.delete(id);
	}
	
    @Override
    public CategoryItemPO findById(Integer id) {
        return this.categoryItemDao.findById(id);
    }

	@Override
	public List<CategoryItemPO> queryCategoryItemListByCategoryId(Integer categoryId) {
		return this.categoryItemDao.queryCategoryItemListByCategoryId(categoryId);
	}

	@Override
	public List<CategoryItemPO> queryCategoryItemList(Page<?> page, Integer typeId, Integer firstCategoryId, Integer secondCategoryId, String itemName) {
		return this.categoryItemDao.queryCategoryItemList(page, typeId, firstCategoryId, secondCategoryId, itemName);
	}

}
