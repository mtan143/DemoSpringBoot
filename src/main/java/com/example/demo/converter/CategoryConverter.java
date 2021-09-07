package com.example.demo.converter;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {


    public CategoryEntity toEntity(CategoryDTO dto, CategoryEntity entity){
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        return entity;
    }
}
