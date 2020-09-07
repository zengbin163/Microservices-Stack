package com.chihuo.food.domain.category.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.category.repository.po.CategoryItemPO;

public interface CategoryItemDao extends BaseMapper<CategoryItemPO> {
	
	void save(@Param("categoryItemPO") CategoryItemPO categoryItemPO);
	
	void update(@Param("categoryItemPO") CategoryItemPO categoryItemPO);
	
	void delete(Integer id);

	CategoryItemPO findById(Integer id);
	
	List<CategoryItemPO> queryCategoryItemListByCategoryId(Integer categoryId);

	List<CategoryItemPO> queryCategoryItemList(Page<?> page, @Param("typeId") Integer typeId,
			@Param("firstCategoryId") Integer firstCategoryId, @Param("secondCategoryId") Integer secondCategoryId,
			@Param("itemName") String itemName);

}
