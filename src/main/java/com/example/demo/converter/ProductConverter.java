package com.example.demo.converter;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductEntity toEntity(ProductDTO dto, ProductEntity entity){
        entity.setName(dto.getName());
        entity.setMaterial(dto.getMaterial());
        entity.setPrice(dto.getPrice());
        entity.setImage(dto.getImage());
        return entity;
    }
}
