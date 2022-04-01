package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryProductService {
    Page<CategoryProduct> findAllCategoryProducts(Pageable pageable);
    CategoryProduct findCategoryProductById(Long id);
    CategoryProduct createCategoryProduct(CategoryProduct categoryProduct);
    CategoryProduct updateCategoryProduct(Long id, CategoryProduct payload);
}
