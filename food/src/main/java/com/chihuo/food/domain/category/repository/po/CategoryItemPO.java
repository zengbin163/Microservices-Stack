package com.chihuo.food.domain.category.repository.po;

import java.util.Date;

import com.chihuo.food.domain.category.entity.CategoryItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryItemPO {
	private Integer id;
	private Integer categoryId;
	private String itemName;
	private Date createTime;
	private Date updateTime;
	
	private CategoryPO categoryPO;//二级分类，分类属性对应的分类对象
	private CategoryPO parentCategoryPO;//一级分类，二级分类对应的一级分类
	private CategoryTypePO categoryTypePO;//分类大类

	public CategoryItem toCategoryItem() {
		return new CategoryItem();
	}
}
