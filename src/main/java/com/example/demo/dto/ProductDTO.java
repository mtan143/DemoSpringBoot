package com.example.demo.dto;


import com.example.demo.entity.ProductEntity;

public class ProductDTO extends BaseDTO<ProductDTO>{

    private String name;

    private String material;

    private Long price;

    private String image;

    private Long categoryId;

    private String categoryCode;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public ProductEntity toEntity(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(this.getName());
        productEntity.setMaterial(this.getMaterial());
        productEntity.setPrice(this.getPrice());
        productEntity.setImage(this.getImage());
        return productEntity;
    }
}
