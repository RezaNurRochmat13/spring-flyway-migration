package com.database.migration.presenter;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.service.CategoryProductService;
import com.database.migration.util.ListResponse;
import com.database.migration.util.MetaResponse;
import com.database.migration.util.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryProductPresenter {
    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private SingleResponse singleResponse;

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCategories(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                   @RequestParam(value= "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryProduct> categoriesList = categoryProductService.findAllCategoryProducts(pageable);

        MetaResponse metaResponse = MetaResponse.builder()
                .count(categoriesList.getSize())
                .total(categoriesList.getTotalElements())
                .page(page)
                .currentPage(categoriesList.getNumber()).build();

        ListResponse listResponse = ListResponse.builder()
                .metaResponse(metaResponse)
                .data(categoriesList.getContent())
                .build();

        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSingleCategoryProduct(@PathVariable Long id) throws Exception {
        CategoryProduct categoryProduct = categoryProductService.findCategoryProductById(id);

        singleResponse.setData(categoryProduct);

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> createCategoryProduct(@Validated @RequestBody CategoryProduct payload) {
        CategoryProduct categoryProductSave = categoryProductService
                .createCategoryProduct(payload);

        singleResponse.setData(categoryProductSave);

        return new ResponseEntity<>(singleResponse, HttpStatus.CREATED);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateCategoryProduct(@PathVariable Long id,
                                                        @RequestBody CategoryProduct payload) {
        CategoryProduct categoryProductUpdate = categoryProductService.updateCategoryProduct(id, payload);

        singleResponse.setData(categoryProductUpdate);

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> deleteCategoryProduct(@PathVariable Long id) {
        categoryProductService.deleteCategoryProduct(id);

        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
