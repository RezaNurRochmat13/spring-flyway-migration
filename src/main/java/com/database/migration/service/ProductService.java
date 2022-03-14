package com.database.migration.service;

import com.database.migration.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAllProducts(Pageable pageable);
    Product findProductById(Long id);
    Product createNewProducts(Product payload);
    Product updateProduct(Long id, Product payload);
    void deleteProduct(Long id);
}
