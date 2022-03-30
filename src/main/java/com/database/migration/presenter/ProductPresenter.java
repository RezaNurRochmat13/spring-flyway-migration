package com.database.migration.presenter;

import com.database.migration.entity.dto.CreateProductDto;
import com.database.migration.entity.dto.DetailProductDto;
import com.database.migration.util.ListResponse;
import com.database.migration.util.MetaResponse;
import com.database.migration.entity.Product;
import com.database.migration.util.SingleResponse;
import com.database.migration.entity.dto.ListProductDto;
import com.database.migration.service.ProductServiceImpl;
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
public class ProductPresenter {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ListResponse listResponse;

    @Autowired
    private MetaResponse metaResponse;

    @Autowired
    private SingleResponse singleResponse;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllProducts(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                 @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ListProductDto> productList = productService.findAllProducts(pageable);

        metaResponse.setCount(productList.getTotalPages());
        metaResponse.setTotal(productList.getTotalElements());
        metaResponse.setPage(page);
        metaResponse.setCurrentPage(productList.getNumber());

        listResponse.setData(productList.getContent());
        listResponse.setMetaResponse(metaResponse);


        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSingleProduct(@PathVariable Long id) {
        DetailProductDto productById = productService.findProductById(id);

        singleResponse.setData(productById);

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewProducts(@RequestBody CreateProductDto payload) {
        Product productCreated = productService.createNewProducts(payload);

        singleResponse.setData(productCreated);

        return new ResponseEntity<>(singleResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateProduct(@PathVariable Long id,
                                                @RequestBody Product payload) {
        Product productUpdated = productService.updateProduct(id, payload);

        singleResponse.setData(productUpdated);

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        singleResponse.setData(null);

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }
}
