package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.repository.CategoryProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryProductServiceImplTest {
    @Autowired
    CategoryProductRepository categoryProductRepository;

    @Autowired
    CategoryProductServiceImpl categoryProductService;

    CategoryProduct CATEGORY_1 = new CategoryProduct(1L, "Sembako", "Sembako");
    CategoryProduct CATEGORY_2 = new CategoryProduct(2L, "Alat tulis", "Alat tulis");
    CategoryProduct CATEGORY_3 = new CategoryProduct(3L, "Alat sawah", "Alat sawah");
    CategoryProduct CATEGORY_4 = new CategoryProduct(4L, "Lain-lain", "Lain-lain");
    CategoryProduct CATEGORY_5 = new CategoryProduct(5L, "Alat sawah", "Alat sawah");

    @Test
    public void findAllCategoryProducts() {
    }

    @Test
    public void findCategoryProductByIdWhenValidIds() {
    }

    @Test
    public void findCategoryProductByIdWhenInvalidIds() {

    }

    @Test
    public void createCategoryProduct() {
    }

    @Test
    public void updateCategoryProduct() {
    }

    @Test
    public void deleteCategoryProduct() {
    }
}