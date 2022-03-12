package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.repository.CategoryProductRepository;
import com.database.migration.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {
    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Override
    public Page<CategoryProduct> findAllCategoryProducts(Pageable pageable) {
        return categoryProductRepository.findAll(pageable);
    }

    @Override
    public CategoryProduct findCategoryProductById(Long id) {
        return categoryProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id : " + id));
    }
}
