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
    private ListResponse listResponse;

    @Autowired
    private SingleResponse singleResponse;

    @Autowired
    private MetaResponse metaResponse;

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCategories(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                   @RequestParam(value= "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryProduct> categoriesList = categoryProductService.findAllCategoryProducts(pageable);

        metaResponse.setCount(categoriesList.getSize());
        metaResponse.setTotal(categoriesList.getTotalElements());
        metaResponse.setPage(page);
        metaResponse.setCurrentPage(categoriesList.getNumber());

        listResponse.setMetaResponse(metaResponse);
        listResponse.setData(categoriesList.getContent());

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
}
