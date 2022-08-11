package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.entity.Product;
import com.database.migration.entity.dto.CreateProductDto;
import com.database.migration.entity.dto.DetailProductDto;
import com.database.migration.entity.dto.ListProductDto;
import com.database.migration.repository.CategoryProductRepository;
import com.database.migration.repository.ProductRepository;
import com.database.migration.util.ModelMapperUtility;
import org.junit.Before;
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
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryProductRepository categoryProductRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    private ModelMapperUtility modelMapperUtility;

    CategoryProduct CATEGORY_1 = new CategoryProduct(1L, "Sembako", "Sembako");
    Product PRODUCT_1 = new Product("Beras", "20000", 2, CATEGORY_1.getId());

    private CreateProductDto createProductDto;

    @Before
    public void setUp() {
        createProductDto = CreateProductDto.builder()
                .name(PRODUCT_1.getName())
                .price(PRODUCT_1.getPrice())
                .qty(PRODUCT_1.getQty())
                .categoryId(PRODUCT_1.getCategoryId())
                .build();
    }

    @Test
    public void findAllProducts() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Product> productPage = mock(Page.class);

        when(productRepository.findAll(pageable)).thenReturn(productPage);

        Page<ListProductDto> listProductDtos = productService.findAllProducts(pageable);

        assertThat(listProductDtos).isNotNull();
    }

    @Test
    public void findProductById() {
        when(categoryProductRepository.findById(CATEGORY_1.getId())).thenReturn(Optional.of(CATEGORY_1));
        when(productRepository.findById(PRODUCT_1.getId())).thenReturn(Optional.of(PRODUCT_1));

        DetailProductDto detailProductDtoExpected = productService
                .findProductById(PRODUCT_1.getId());

        assertThat(detailProductDtoExpected).isNotNull();
    }

//    @Test
//    public void createProduct() {
//        when(productRepository.save(PRODUCT_1)).thenReturn(PRODUCT_1);
//        when(modelMapperUtility.modelMapperUtil().map(createProductDto, Product.class));
//
//        Product productExpected = productService.createNewProducts(createProductDto);
//
//        assertThat(productExpected).isNotNull();
//    }

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