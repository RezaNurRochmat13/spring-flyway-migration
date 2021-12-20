package com.database.migration.service;

import com.database.migration.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
}
