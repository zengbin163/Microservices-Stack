package com.chihuo.food.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.category.entity.CategoryItem;
import com.chihuo.food.domain.category.entity.CategoryType;
import com.chihuo.food.domain.category.service.CategoryDomainService;

@Service
public class CategoryApplicationService {
	
    @Autowired
    private CategoryDomainService categoryDomainService;

    public void createCategory(Category category){
    	this.categoryDomainService.create(category);
    }
    
    public void updateCategory(Category category){
    	this.categoryDomainService.update(category);
    }
    
    public Category findById(Integer id) {
        return this.categoryDomainService.findById(id);
    }

    public List<Category> queryCategoryListByParentId(Integer parentId) {
    	return this.categoryDomainService.queryCategoryListByParentId(parentId);
    }
    
    public List<CategoryType> queryCategoryTypeList() {
    	return this.categoryDomainService.queryCategoryTypeList();
    }
    
    public void createItem(CategoryItem categoryItem) {
    	this.categoryDomainService.createItem(categoryItem);
    }

    public void updateItem(CategoryItem categoryItem) {
    	this.categoryDomainService.updateItem(categoryItem);
    }
    
    public void deleteItem(Integer id) {
    	this.categoryDomainService.deleteItem(id);
    }

	public CategoryItem findItemById(Integer id) {
		return this.categoryDomainService.findItemById(id);
	}

	public List<CategoryItem> queryCategoryItemListByCategoryId(Integer categoryId) {
		return this.categoryDomainService.queryCategoryItemListByCategoryId(categoryId);
	}

	public IPage<CategoryItem> queryCategoryItemList(Integer current, Integer size, Integer typeId, Integer firstCategoryId, Integer secondCategoryId, String itemName) {
		return this.categoryDomainService.queryCategoryItemList(current, size, typeId, firstCategoryId, secondCategoryId, itemName);
	}
}
