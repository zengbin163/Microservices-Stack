package com.chihuo.food.interfaces.assembler;

import java.util.ArrayList;
import java.util.List;

import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.domain.food.entity.FoodItem;
import com.chihuo.food.interfaces.dto.FoodDTO;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;

public class FoodAssembler {

	public static FoodDTO toDTO(Food food) {
		FoodDTO dto = new FoodDTO();
		dto.setUid(food.getUid());
		dto.setCategoryId(food.getCategory() != null ? food.getCategory().getId() : null);
		List<FoodItem> foodItemList = food.getFoodItemList();
		if(CollectionUtil.isNotEmpty(foodItemList)) {
			Integer []itemArray = ArrayUtil.newArray(Integer.class, foodItemList.size());
			for (int i = 0; i < foodItemList.size(); i++) {
				FoodItem item = foodItemList.get(i);
				itemArray[i] = item.getCategoryItemId();
			}
			dto.setItemArray(itemArray);
		}
		dto.setFoodName(food.getFoodName());
		dto.setFoodPic(food.getFoodPic());
		dto.setFoodInfo(food.getFoodInfo());
		dto.setCreateTime(food.getCreateTime());
		dto.setUpdateTime(food.getUpdateTime());
		return dto;
	}

	public static Food toDO(FoodDTO dto) {
		Food food = new Food();
		food.setUid(dto.getUid());
		if(null != dto.getCategoryId()) {
			Category category = new Category();
			category.setId(dto.getCategoryId());
			food.setCategory(category);
		}
		List<FoodItem> foodItemList = new ArrayList<FoodItem>();
		Integer []itemArray = dto.getItemArray();
		if(ArrayUtil.isNotEmpty(itemArray)) {
			for(Integer itemId : itemArray) {
				FoodItem foodItem = new FoodItem();
				foodItem.setCategoryItemId(itemId);
				foodItemList.add(foodItem);
			}
			food.setFoodItemList(foodItemList);
		}
		food.setFoodName(dto.getFoodName());
		food.setFoodPic(dto.getFoodPic());
		food.setFoodInfo(dto.getFoodInfo());
		food.setCreateTime(dto.getCreateTime());
		food.setUpdateTime(dto.getUpdateTime());
		return food;
	}
	
}
