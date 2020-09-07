package com.chihuo.food.interfaces.facade.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chihuo.food.application.service.FoodApplicationService;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.interfaces.assembler.FoodAssembler;
import com.chihuo.food.interfaces.dto.FoodDTO;

@RestController
@RequestMapping("/food")
public class FoodApi {
    
    @Autowired
    private FoodApplicationService foodApplicationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Response save(FoodDTO foodDTO) throws Exception {
        this.foodApplicationService.createFood(FoodAssembler.toDO(foodDTO));
        return Response.ok();
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(FoodDTO foodDTO) {
        this.foodApplicationService.updateFood(FoodAssembler.toDO(foodDTO));
    	return Response.ok();
    }
    
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Food findById(@RequestParam(value = "uid") Long uid) {
    	return this.foodApplicationService.findById(uid);
    }
    
	@RequestMapping(value = "/findFoodList", method = RequestMethod.POST)
    @ResponseBody
	public IPage<Food> findFoodList(FoodDTO foodDTO) {
		return this.foodApplicationService.queryFoodList(foodDTO.getCurrent(), foodDTO.getSize(), foodDTO.getFirstCategoryId(), foodDTO.getSecondCategoryId(), foodDTO.getFoodName());
	}

}
