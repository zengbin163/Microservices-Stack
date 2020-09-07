package com.chihuo.food.interfaces.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryDTO {
    private Integer id;
    private Integer categoryTypeId;
    private Integer parentId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
}
