package com.chihuo.food.domain.category.repository.po;

import java.util.Date;

import com.chihuo.food.domain.category.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPO {
	private Integer id;
	private Integer categoryTypeId;
	private Integer parentId;
	private String categoryName;
	private Date createTime;
	private Date updateTime;

	public Category toCategory() {
		return new Category();
	}
}
