package com.database.migration.service;

import com.database.migration.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAllProducts(Pageable pageable);
    Optional<Product> findProductById(Long id);
}
