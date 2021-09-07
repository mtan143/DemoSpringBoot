package com.example.demo.dto;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;

import java.util.List;

public class CategoryDTO extends BaseDTO<CategoryDTO>{
    private String name;
    private String code;
    private List<ProductDTO> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public CategoryEntity toEntity(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(this.getName());
        categoryEntity.setCode(this.getCode());
        return categoryEntity;
    }
}
