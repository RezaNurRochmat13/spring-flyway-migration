package com.database.migration.presenter;

import com.database.migration.entity.Product;
import com.database.migration.entity.dto.CreateProductDto;
import com.database.migration.entity.dto.DetailProductDto;
import com.database.migration.entity.dto.ListProductDto;
import com.database.migration.service.ProductServiceImpl;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductPresenter {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllProducts(@RequestParam(value = "page", defaultValue = "0", required = true) Integer page,
                                                 @RequestParam(value = "size", defaultValue = "10", required = true) Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ListProductDto> productList = productService.findAllProducts(pageable);

        MetaResponse metaResponse = MetaResponse.builder()
                .count(productList.getSize())
                .total(productList.getTotalElements())
                .page(page)
                .currentPage(productList.getNumber())
                .build();

        ListResponse listResponse = ListResponse.builder()
                .data(productList.getContent())
                .metaResponse(metaResponse)
                .build();

        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSingleProduct(@PathVariable Long id) {
        DetailProductDto productById = productService.findProductById(id);

        SingleResponse singleResponse = SingleResponse.builder()
                .data(productById)
                .build();

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewProducts(@RequestBody CreateProductDto payload) {
        Product productCreated = productService.createNewProducts(payload);

        SingleResponse singleResponse = SingleResponse.builder()
                .data(productCreated)
                .build();

        return new ResponseEntity<>(singleResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateProduct(@PathVariable Long id,
                                                @RequestBody Product payload) {
        Product productUpdated = productService.updateProduct(id, payload);

        SingleResponse singleResponse = SingleResponse.builder()
                .data(productUpdated)
                .build();

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        SingleResponse singleResponse = SingleResponse.builder()
                .data(null)
                .build();

        return new ResponseEntity<>(singleResponse, HttpStatus.OK);
    }
}
