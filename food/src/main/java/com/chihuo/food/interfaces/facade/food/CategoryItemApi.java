package com.chihuo.food.interfaces.facade.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chihuo.food.application.service.CategoryApplicationService;
import com.chihuo.food.domain.category.entity.CategoryItem;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.interfaces.assembler.CategoryAssembler;
import com.chihuo.food.interfaces.dto.CategoryItemDTO;

@RestController
@RequestMapping("/categoryItem")
public class CategoryItemApi {
    
    @Autowired
    private CategoryApplicationService categoryApplicationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Response save(CategoryItemDTO categoryItemDTO) {
        this.categoryApplicationService.createItem(CategoryAssembler.toItemDO(categoryItemDTO));
        return Response.ok();
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(CategoryItemDTO categoryItemDTO) {
    	this.categoryApplicationService.updateItem(CategoryAssembler.toItemDO(categoryItemDTO));
    	return Response.ok();
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestParam(value = "id") Integer id) {
    	this.categoryApplicationService.deleteItem(id);
    	return Response.ok();
    }
    
    @RequestMapping(value = "/findItemById", method = RequestMethod.GET)
    public CategoryItem findItemById(@RequestParam(value = "id") Integer id) {
    	return this.categoryApplicationService.findItemById(id);
    }
    
    @RequestMapping(value = "/findItemByCategoryId", method = RequestMethod.GET)
    public List<CategoryItem> findItemByCategoryId(@RequestParam(value = "categoryId") Integer categoryId) {
    	return this.categoryApplicationService.queryCategoryItemListByCategoryId(categoryId);
    }
    
	@RequestMapping(value = "/findItemList", method = RequestMethod.POST)
    @ResponseBody
	public IPage<CategoryItem> findItemList(CategoryItemDTO categoryItemDTO) {
		return this.categoryApplicationService.queryCategoryItemList(categoryItemDTO.getCurrent(), categoryItemDTO.getSize(), categoryItemDTO.getCategoryTypeId(), categoryItemDTO.getFirstCategoryId(), categoryItemDTO.getSecondCategoryId(), categoryItemDTO.getItemName());
	}

}
