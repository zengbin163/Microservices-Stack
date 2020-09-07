package com.chihuo.food.interfaces.facade.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.application.service.CategoryApplicationService;
import com.chihuo.food.domain.category.entity.CategoryType;

@RestController
@RequestMapping("/categoryType")
public class CategoryTypeApi {
    
    @Autowired
    private CategoryApplicationService categoryApplicationService;

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    public List<CategoryType> findList() {
    	return this.categoryApplicationService.queryCategoryTypeList();
    }

}
