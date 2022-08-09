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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CategoryProductServiceImplTest {
    @Mock
    CategoryProductRepository categoryProductRepository;

    @InjectMocks
    CategoryProductServiceImpl categoryProductService;

    CategoryProduct CATEGORY_1 = new CategoryProduct(1L, "Sembako", "Sembako");

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
        given(categoryProductRepository.save(CATEGORY_1))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        CategoryProduct categoryProductExpected = categoryProductService.createCategoryProduct(CATEGORY_1);

        assertThat(categoryProductExpected).isNotNull();

        verify(categoryProductRepository).save(CATEGORY_1);

    }

    @Test
    public void updateCategoryProduct() {
        given(categoryProductRepository.save(CATEGORY_1)).willReturn(CATEGORY_1);
        given(categoryProductRepository.findById(CATEGORY_1.getId())).willReturn(Optional.of(CATEGORY_1));

        CategoryProduct categoryProductExpected = categoryProductService
                .updateCategoryProduct(CATEGORY_1.getId(), CATEGORY_1);

        assertThat(categoryProductExpected).isNotNull();
    }

    @Test
    public void deleteCategoryProduct() {
        when(categoryProductRepository.findById(CATEGORY_1.getId())).thenReturn(Optional.of(CATEGORY_1));

        categoryProductService.deleteCategoryProduct(CATEGORY_1.getId());

        verify(categoryProductRepository, times(1)).delete(CATEGORY_1);
    }
}