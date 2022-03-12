package com.database.migration.presenter;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.entity.ListResponse;
import com.database.migration.entity.MetaResponse;
import com.database.migration.entity.SingleResponse;
import com.database.migration.service.CategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

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
    public ResponseEntity<Object> getAllCategories(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "0") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryProduct> categoriesList = categoryProductService.findAllCategoryProducts(pageable);

        metaResponse.setCount(categoriesList.getTotalPages());
        metaResponse.setTotal(categoriesList.getTotalElements());
        metaResponse.setPage(page);
        metaResponse.setCurrentPage(categoriesList.getNumber());

        listResponse.setMetaResponse(metaResponse);
        listResponse.setData(Collections.singletonList(categoriesList));

        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSingleCategoryProduct(@PathVariable Long id) throws Exception {
        CategoryProduct categoryProduct = categoryProductService.findCategoryProductById(id);

        singleResponse.setData(categoryProduct);

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }
}
