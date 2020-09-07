package com.chihuo.food.domain.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.category.entity.CategoryItem;
import com.chihuo.food.domain.category.entity.CategoryType;
import com.chihuo.food.domain.category.repository.facade.CategoryRepository;
import com.chihuo.food.domain.category.repository.po.CategoryItemPO;
import com.chihuo.food.domain.category.repository.po.CategoryPO;
import com.chihuo.food.domain.category.repository.po.CategoryTypePO;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;

@Service
public class CategoryFactory {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryPO createCategoryPO(Category category){
    	BeanCopier<CategoryPO> copier = BeanCopier.create(category, new CategoryPO(), CopyOptions.create());
    	return copier.copy();
    }

	public Category createCategory(CategoryPO po) {
    	BeanCopier<Category> copier = BeanCopier.create(po, new Category(), CopyOptions.create());
    	return copier.copy();
	}
	
	public CategoryItemPO createCategoryItemPO(CategoryItem categoryItem){
    	BeanCopier<CategoryItemPO> copier = BeanCopier.create(categoryItem, new CategoryItemPO(), CopyOptions.create());
    	return copier.copy();
	}
	
	public CategoryItem createCategoryItem(CategoryItemPO po) {
    	BeanCopier<CategoryItem> copier = BeanCopier.create(po, new CategoryItem(), CopyOptions.create());
    	return copier.copy();
	}
	
	public CategoryType createCategoryType(CategoryTypePO po) {
    	BeanCopier<CategoryType> copier = BeanCopier.create(po, new CategoryType(), CopyOptions.create());
    	return copier.copy();
	}
	
	public List<Category> createCategoryList(List<CategoryPO> poList) {
		List<Category> list = new ArrayList<Category>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(CategoryPO po : poList) {
			list.add(createCategory(po));
		}
		return list;
	}
	
	public List<CategoryType> createCategoryTypeList(List<CategoryTypePO> poList) {
		List<CategoryType> list = new ArrayList<CategoryType>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(CategoryTypePO po : poList) {
			list.add(this.createCategoryType(po));
		}
		return list;
	}
	
	public List<CategoryItem> createCategoryItemList(List<CategoryItemPO> poList) {
		List<CategoryItem> list = new ArrayList<CategoryItem>();
		if(CollectionUtils.isEmpty(poList)) {
			return list;
		}
		for(CategoryItemPO po : poList) {
			CategoryItem categoryItem = this.createCategoryItem(po);
			CategoryTypePO typePO = po.getCategoryTypePO();
			if(typePO != null) {
				categoryItem.setCategoryType(createCategoryType(typePO));
			}
			CategoryPO parentCategoryPO = po.getParentCategoryPO();
			if(parentCategoryPO != null) {
				categoryItem.setParentCategory(createCategory(parentCategoryPO));
			}
			CategoryPO categoryPO = po.getCategoryPO();
			if(categoryPO != null) {
				categoryItem.setCategory(createCategory(categoryPO));
			}
			list.add(categoryItem);
		}
		return list;
	}
	
	public IPage<CategoryItem> createCateItemPage(IPage<CategoryItemPO> pagePO) {
		IPage<CategoryItem> page = new Page<CategoryItem>();
		page.setCurrent(pagePO.getCurrent());
		page.setPages(pagePO.getPages());
		page.setRecords(this.createCategoryItemList(pagePO.getRecords()));
		page.setSize(pagePO.getSize());
		page.setTotal(pagePO.getTotal());
		return page;
	}

    public Category getCategory(CategoryPO categoryPO){
    	categoryPO = categoryRepository.findById(categoryPO.getId());
        return createCategory(categoryPO);
    }

}
