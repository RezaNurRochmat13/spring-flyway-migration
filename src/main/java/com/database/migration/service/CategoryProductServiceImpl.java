package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.repository.CategoryProductRepository;
import com.database.migration.util.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Slf4j
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

    @Override
    @Transactional
    public CategoryProduct createCategoryProduct(CategoryProduct categoryProduct) {
        return categoryProductRepository.save(categoryProduct);
    }

    @Override
    @Transactional
    public CategoryProduct updateCategoryProduct(Long id, CategoryProduct payload) {
        CategoryProduct categoryProductById = categoryProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id : " + id));

        categoryProductById.setName(payload.getName());
        categoryProductById.setDescription(payload.getDescription());
        categoryProductRepository.save(categoryProductById);

        return categoryProductById;
    }

    @Override
    @Transactional
    public void deleteCategoryProduct(Long id) {
        CategoryProduct categoryProductById = categoryProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id : " + id));

        categoryProductRepository.delete(categoryProductById);
    }
}
