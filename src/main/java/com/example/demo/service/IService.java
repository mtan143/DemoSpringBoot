package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IService {

    //Product
    ProductDTO save(ProductDTO productDTO);
    void delete(long[] ids);
    List<ProductDTO> findAll(Pageable pageable, Long categoryId);
    ProductDTO getProduct(Long id);
//    List<ProductDTO> findAll(Pageable pageable, Long categoryId, Long gte, Long lte);
    List<ProductDTO> findAll(Pageable pageable, Long gte, Long lte);
    List<ProductDTO> findAll(Pageable pageable);
    int totalItem();
    ResponseEntity showProduct(int page, int limit, String sort, Long categoryId, Long gte,Long lte);
    List<ProductDTO> findProductByCategoryId(Long categoryId);

    //Category
    List<CategoryDTO> getAllCategory();
}
