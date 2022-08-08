package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.repository.CategoryProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


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
        Pageable pageable = PageRequest.of(0, 10);

        Page<CategoryProduct> categoryProductPage = mock(Page.class);

        given(categoryProductRepository.findAll(pageable)).willReturn(categoryProductPage);

        Page<CategoryProduct> categoryProductPageExpected = categoryProductService
                .findAllCategoryProducts(pageable);


        assertThat(categoryProductPageExpected).isNotNull();
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