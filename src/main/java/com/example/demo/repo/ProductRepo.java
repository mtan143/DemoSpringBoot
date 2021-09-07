package com.example.demo.repo;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findProductByCategoryId(Long categoryId);
    List<ProductEntity> findAllByCategoryId(Pageable pageable, Long categoryId);
    int countProductEntitiesByCategoryId(Long categoryId);
}
