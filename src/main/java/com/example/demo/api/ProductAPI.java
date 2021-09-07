package com.example.demo.api;

import com.example.demo.api.output.ProductOut;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.service.IService;
import com.example.demo.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class ProductAPI {

    @Autowired
    @Qualifier("impService")
    private IService service;


    @GetMapping("/product")
    public ResponseEntity showProduct(@RequestParam("page") int page,
                                      @RequestParam("limit") int limit,
                                      @RequestParam("sort") String sort,
                                      @RequestParam(required = false) Long categoryId,
                                      @RequestParam(required = false) Long gte,
                                      @RequestParam(required = false) Long lte
    ){
        return service.showProduct(page, limit, sort, categoryId, gte, lte);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProduct(@PathVariable Long id){
        ProductDTO result = service.getProduct(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody ProductDTO model){
        service.save(model);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity updateProduct(@RequestBody ProductDTO model, @PathVariable("id") Long id){
        model.setId(id);
        service.save(model);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/product")
    public void deleteProduct(@RequestBody long[] ids){
        service.delete(ids);
    }




    @GetMapping("/category")
    public List<CategoryDTO> getAll(){
        return service.getAllCategory();
    }


    @GetMapping("/product/find")
    public List<ProductDTO> findProductByCategoryId(@RequestParam("categoryId") Long categoryId){
        return service.findProductByCategoryId(categoryId);
    }

}
