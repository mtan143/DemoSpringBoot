package com.example.demo.repo;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findOneByCode(String code);
}
