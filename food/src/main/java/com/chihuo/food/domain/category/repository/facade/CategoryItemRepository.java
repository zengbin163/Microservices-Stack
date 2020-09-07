package com.chihuo.food.domain.category.repository.facade;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.category.repository.po.CategoryItemPO;

public interface CategoryItemRepository {

	Integer save(CategoryItemPO categoryItemPO);

	void update(CategoryItemPO categoryItemPO);
	
	void delete(Integer id);

	CategoryItemPO findById(Integer id);

	List<CategoryItemPO> queryCategoryItemListByCategoryId(Integer categoryId);

	List<CategoryItemPO> queryCategoryItemList(Page<?> page, Integer typeId, Integer firstCategoryId, Integer secondCategoryId, String itemName);

}