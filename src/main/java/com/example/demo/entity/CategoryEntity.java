package com.example.demo.entity;

import com.example.demo.dto.CategoryDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();


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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public CategoryDTO toDTO(){
        CategoryDTO categoryDTO = new CategoryDTO();
        if (this.getId() != null){
            categoryDTO.setId(this.getId());
        }
        categoryDTO.setName(this.getName());
        categoryDTO.setCode(this.getCode());
        categoryDTO.setCreatedDate(this.getCreatedDate());
        categoryDTO.setCreatedBy(this.getCreatedBy());
        categoryDTO.setModifiedDate(this.getModifiedDate());
        categoryDTO.setModifiedBy(this.getModifiedBy());
        return categoryDTO;
    }
}
