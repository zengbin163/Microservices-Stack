package com.chihuo.food.interfaces.facade.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.application.service.CategoryApplicationService;
import com.chihuo.food.domain.category.entity.Category;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.interfaces.assembler.CategoryAssembler;
import com.chihuo.food.interfaces.dto.CategoryDTO;

@RestController
@RequestMapping("/category")
public class CategoryApi {
    
    @Autowired
    private CategoryApplicationService categoryApplicationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Response save(CategoryDTO categoryDTO) {
        this.categoryApplicationService.createCategory(CategoryAssembler.toDO(categoryDTO));
        return Response.ok();
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(CategoryDTO categoryDTO) {
    	this.categoryApplicationService.updateCategory(CategoryAssembler.toDO(categoryDTO));
    	return Response.ok();
    }
    
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Category findById(@RequestParam(value = "id") Integer id) {
    	return this.categoryApplicationService.findById(id);
    }
    
    @RequestMapping(value = "/findByPid", method = RequestMethod.GET)
    public List<Category> findByPid(@RequestParam(value = "parentId") Integer parentId) {
    	return this.categoryApplicationService.queryCategoryListByParentId(parentId);
    }

}
