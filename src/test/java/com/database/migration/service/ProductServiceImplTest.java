package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.entity.Product;
import com.database.migration.entity.dto.CreateProductDto;
import com.database.migration.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    CategoryProduct CATEGORY_1 = new CategoryProduct(1L, "Sembako", "Sembako");
    Product PRODUCT_1 = new Product("Beras", "20000", 2, CATEGORY_1.getId());

    @Test
    public void findAllProducts() {
    }

    @Test
    public void findProductById() {
    }

    @Test
    public void createNewProducts() {
    }

    @Test
    public void updateProduct() {
        given(productRepository.save(PRODUCT_1)).willReturn(PRODUCT_1);
        given(productRepository.findById(PRODUCT_1.getId())).willReturn(Optional.of(PRODUCT_1));

        Product productExpected = productService
                .updateProduct(PRODUCT_1.getId(), PRODUCT_1);

        assertThat(productExpected).isNotNull();
    }

    @Test
    public void deleteProduct() {
        when(productRepository.findById(PRODUCT_1.getId())).thenReturn(Optional.of(PRODUCT_1));

        productService.deleteProduct(PRODUCT_1.getId());

        verify(productRepository, times(1)).delete(PRODUCT_1);
    }
}