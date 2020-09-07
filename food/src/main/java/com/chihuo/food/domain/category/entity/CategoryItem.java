package com.chihuo.food.domain.category.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryItem {
	private Integer id;
	private Integer categoryId;
	private String itemName;
	private Date createTime;
	private Date updateTime;
	
	private Category category;//二级分类，分类属性对应的分类对象
	private Category parentCategory;//一级分类，二级分类对应的一级分类
	private CategoryType categoryType;//分类大类
}
