package com.example.demo.entity;

import com.example.demo.dto.ProductDTO;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "material")
    private String material;

    @Column(name = "price")
    private Long price;

    @Column(name = "image")
    private String image;


    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public ProductDTO toDTO(){
        ProductDTO productDTO = new ProductDTO();
        if (this.getId() != null){
            productDTO.setId(this.getId());
        }
        productDTO.setName(this.getName());
        productDTO.setMaterial(this.getMaterial());
        productDTO.setPrice(this.getPrice());
        productDTO.setImage(this.getImage());
        productDTO.setCreatedDate(this.getCreatedDate());
        productDTO.setCreatedBy(this.getCreatedBy());
        productDTO.setModifiedDate(this.getModifiedDate());
        productDTO.setModifiedBy(this.getModifiedBy());
        return productDTO;
    }
}
