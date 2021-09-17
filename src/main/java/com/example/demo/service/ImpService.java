package com.example.demo.service;
import com.example.demo.api.output.ProductOut;
import com.example.demo.converter.CategoryConverter;
import com.example.demo.converter.ProductConverter;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductRepo;
import com.example.demo.validator.ProductValidator;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//impService
@Service
public class ImpService implements IService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductConverter converter;


    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity entity = new ProductEntity();
        if(productDTO.getId() != null){
            ProductEntity oldEntity = productRepo.findById(productDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + productDTO.getId()));
            entity = converter.toEntity(productDTO, oldEntity);
        }
        CategoryEntity categoryEntity = categoryRepo.findOneByCode(productDTO.getCategoryCode());
        entity.setCategory(categoryEntity);
        entity = productRepo.save(entity);
        return entity.toDTO();
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids){
            productRepo.deleteById(id);
        }
    }


    //SORT, PAGE, LIMIT
    @Override
    public List<ProductDTO> findAll(Pageable pageable){
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> entities = productRepo.findAll(pageable).getContent();
        entities.forEach(item->{
            results.add(item.toDTO());
        });
        return results;
    }

    //SORT, PAGE, LIMIT, CATEGORY
    @Override
    public List<ProductDTO> findAll(Pageable pageable, Long categoryId) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> entities = productRepo.findAllByCategoryId(pageable, categoryId);
        entities.forEach(item -> {
            results.add(item.toDTO());
        });
        return results;
    }

    @Override
    public ProductDTO getProduct(Long id) {
        ProductEntity entity = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return entity.toDTO();
    }

    //SORT, PAGE, LIMIT, GTE LTE
    @Override
    public List<ProductDTO> findAll(Pageable pageable, Long gte, Long lte) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> entities = productRepo
                .findAll(pageable).stream().filter(product ->
            product.getPrice() >= gte && product.getPrice() <= lte
        ).collect(Collectors.toList());
        entities.forEach(item -> {
            results.add(item.toDTO());
        });
        return results;
    }


    @Override
    public ResponseEntity showProduct(int page, int limit, String sort, Long categoryId, Long gte, Long lte){

        ProductOut result = new ProductOut();
        //validation
        //page > -1
        ProductValidator.validate(page, limit, sort);

        result.setPage(page);

        Pageable pageable = null;

        //build sorting criteria
        Sort sorting = Sort.by("price").ascending();
        if (sort.equals("desc")) {
            sorting = Sort.by("price").descending();
        }
        pageable = PageRequest.of(page - 1, limit, sorting);

        if (limit < 1) {//number of item per page is invalid
            return null;
        }

        //calculate total item
        int totalProduct = totalItem();

        if (totalProduct < 1) {
            return ResponseEntity.ok("empty item");
        }
        //paging calculator
        result.setTotalPage((int)Math.ceil((double) (totalProduct)/limit));
        result.setTotalItem(totalProduct);

        //PAGE, LIMIT, CATEGORY
        if(categoryId != null) {//search by CATEGORY criteria
            result.setListResult(findAll(pageable, categoryId));
            return ResponseEntity.ok(result);
        }

        if(gte != null && lte != null) {//search by PRICE criteria
            result.setListResult(findAll(pageable, gte, lte));
            return ResponseEntity.ok(result);
        }

        result.setListResult(findAll(pageable));

        return ResponseEntity.ok(result);
    }


    @Override
    public int totalItem() {
        return (int)productRepo.count();
    }

    @Override
    public List<ProductDTO> findProductByCategoryId(Long categoryId) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> entities = productRepo.findProductByCategoryId(categoryId);
        entities.forEach(item -> {
            results.add(item.toDTO());
        });
        return results;
    }



    //CATEGORY SERVICE
    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepo.findAll();

        entities.forEach(item -> {
            CategoryDTO categoryDTO = item.toDTO();
            categoryDTO.setProducts(findProductByCategoryId(item.getId()));
            results.add(categoryDTO);
        });
        return results;
    }


}
