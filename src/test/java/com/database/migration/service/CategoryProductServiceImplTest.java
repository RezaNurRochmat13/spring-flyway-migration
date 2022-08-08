package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.repository.CategoryProductRepository;
import com.database.migration.util.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CategoryProductServiceImplTest {
    @Mock
    CategoryProductRepository categoryProductRepository;

    @InjectMocks
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
        given(categoryProductRepository.findById(CATEGORY_1.getId())).willReturn(Optional.of(CATEGORY_1));

        CategoryProduct categoryProductExpected = categoryProductService
                .findCategoryProductById(CATEGORY_1.getId());

        assertThat(categoryProductExpected).isNotNull();
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