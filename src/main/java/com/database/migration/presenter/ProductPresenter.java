package com.database.migration.presenter;

import com.database.migration.entity.ListResponse;
import com.database.migration.entity.MetaResponse;
import com.database.migration.entity.Product;
import com.database.migration.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1")
public class ProductPresenter {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ListResponse listResponse;

    @Autowired
    private MetaResponse metaResponse;

    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<Object> getAllProducts(@RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "0") Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productList = productService.findAllProducts(pageable);

        metaResponse.setCount(productList.getTotalPages());
        metaResponse.setTotal(productList.getTotalElements());
        metaResponse.setPage(page);
        metaResponse.setCurrentPage(productList.getNumber());

        listResponse.setData(Collections.singletonList(productList.getContent()));
        listResponse.setMetaResponse(metaResponse);


        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }
}
