package com.chihuo.food.domain.food.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.category.repository.po.CategoryPO;
import com.chihuo.food.domain.category.service.CategoryFactory;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.domain.food.entity.FoodItem;
import com.chihuo.food.domain.food.entity.FoodStock;
import com.chihuo.food.domain.food.entity.Promotion;
import com.chihuo.food.domain.food.repository.po.FoodItemPO;
import com.chihuo.food.domain.food.repository.po.FoodPO;
import com.chihuo.food.domain.food.repository.po.FoodStockPO;
import com.chihuo.food.domain.food.repository.po.PromotionPO;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;

@Service
public class FoodFactory {
	
	@Autowired
	private CategoryFactory categoryFactory;
	
    public FoodPO createFoodPO(Food food) {
    	BeanCopier<FoodPO> copier = BeanCopier.create(food, new FoodPO(), CopyOptions.create());
    	return copier.copy();
    }

	public Food createFood(FoodPO po) {
    	BeanCopier<Food> copier = BeanCopier.create(po, new Food(), CopyOptions.create());
    	return copier.copy();
	}
	
	public FoodItemPO createFoodItemPO(FoodItem foodItem) {
    	BeanCopier<FoodItemPO> copier = BeanCopier.create(foodItem, new FoodItemPO(), CopyOptions.create());
    	return copier.copy();
	}
	
	public FoodItem createFoodItem(FoodItemPO foodItemPO) {
    	BeanCopier<FoodItem> copier = BeanCopier.create(foodItemPO, new FoodItem(), CopyOptions.create());
    	return copier.copy();
	}
	
	public List<Food> createFoodList(List<FoodPO> poList) {
		List<Food> list = new ArrayList<Food>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(FoodPO po : poList) {
			Food food = this.createFood(po);
			CategoryPO categoryPO = po.getCategory();
			if(categoryPO != null) {
				food.setCategory(this.categoryFactory.createCategory(categoryPO));
			}
			CategoryPO parentCategoryPO = po.getParentCategory();
			if(parentCategoryPO != null) {
				food.setParentCategory(this.categoryFactory.createCategory(parentCategoryPO));
			}
			list.add(food);
		}
		return list;
	}
	
	public IPage<Food> createFoodPage(IPage<FoodPO> pagePO) {
		IPage<Food> page = new Page<Food>();
		page.setCurrent(pagePO.getCurrent());
		page.setPages(pagePO.getPages());
		page.setRecords(this.createFoodList(pagePO.getRecords()));
		page.setSize(pagePO.getSize());
		page.setTotal(pagePO.getTotal());
		return page;
	}
	
	public FoodStock createFoodStock(FoodStockPO foodStockPO) {
		BeanCopier<FoodStock> copier = BeanCopier.create(foodStockPO, new FoodStock(), CopyOptions.create());
		return copier.copy();
	}

	public Promotion createPromotion(PromotionPO promotionPO) {
		BeanCopier<Promotion> copier = BeanCopier.create(promotionPO, new Promotion(), CopyOptions.create());
		return copier.copy();
	}
	
}
