package com.chihuo.food.domain.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.domain.category.entity.CategoryItem;
import com.chihuo.food.domain.category.entity.CategoryType;
import com.chihuo.food.domain.category.repository.facade.CategoryItemRepository;
import com.chihuo.food.domain.category.repository.facade.CategoryRepository;
import com.chihuo.food.domain.category.repository.facade.CategoryTypeRepository;
import com.chihuo.food.domain.category.repository.po.CategoryItemPO;
import com.chihuo.food.domain.category.repository.po.CategoryPO;
import com.chihuo.food.domain.category.repository.po.CategoryTypePO;

@Service
public class CategoryDomainService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryTypeRepository categoryTypeRepository;
    @Autowired
    private CategoryItemRepository categoryItemRepository;
    @Autowired
    private CategoryFactory categoryFactory;


    public void create(Category category) {
    	CategoryPO categoryPO = categoryRepository.findById(category.getId());
        if (null != categoryPO) {
            throw new RuntimeException("Category already exists");
        }
        this.categoryRepository.save(categoryFactory.createCategoryPO(category));
    }

    public void update(Category category) {
    	this.categoryRepository.update(categoryFactory.createCategoryPO(category));
    }

    public Category findById(Integer id) {
    	CategoryPO categoryPO = categoryRepository.findById(id);
        return this.categoryFactory.getCategory(categoryPO);
    }

    public List<Category> queryCategoryListByParentId(Integer parentId) {
    	if(null == parentId) {
    		throw new IllegalArgumentException("parentId is null");
    	}
    	List<CategoryPO> poList = this.categoryRepository.queryCategoryListByParentId(parentId);
    	return this.categoryFactory.createCategoryList(poList);
    }
    
    public List<CategoryType> queryCategoryTypeList() {
    	List<CategoryTypePO> poList = this.categoryTypeRepository.queryCategoryTypeList();
    	return this.categoryFactory.createCategoryTypeList(poList);
    }
    
    public void createItem(CategoryItem categoryItem) {
    	this.categoryItemRepository.save(categoryFactory.createCategoryItemPO(categoryItem));
    }

    public void updateItem(CategoryItem categoryItem) {
    	this.categoryItemRepository.update(categoryFactory.createCategoryItemPO(categoryItem));
    }
    
    public void deleteItem(Integer id) {
    	this.categoryItemRepository.delete(id);
    }

	public CategoryItem findItemById(Integer id) {
		if(null == id) {
    		throw new IllegalArgumentException("id is null");
		}
		CategoryItemPO categoryItemPO = this.categoryItemRepository.findById(id);
		return this.categoryFactory.createCategoryItem(categoryItemPO);
	}

	public List<CategoryItem> queryCategoryItemListByCategoryId(Integer categoryId) {
		if(null == categoryId) {
    		throw new IllegalArgumentException("categoryId is null");
		}
		List<CategoryItemPO> poList = this.categoryItemRepository.queryCategoryItemListByCategoryId(categoryId);
		return this.categoryFactory.createCategoryItemList(poList);
	}

	public IPage<CategoryItem> queryCategoryItemList(Integer current, Integer size, Integer typeId, Integer firstCategoryId, Integer secondCategoryId, String itemName) {
		Page<CategoryItem> page = new Page<CategoryItem>(current, size);
		List<CategoryItemPO> records = this.categoryItemRepository.queryCategoryItemList(page, typeId, firstCategoryId, secondCategoryId, itemName);
		page.setRecords(this.categoryFactory.createCategoryItemList(records));
		return page;
	}

}