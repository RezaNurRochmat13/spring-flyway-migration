package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.entity.Product;
import com.database.migration.entity.dto.CreateProductDto;
import com.database.migration.entity.dto.DetailProductDto;
import com.database.migration.entity.dto.ListProductDto;
import com.database.migration.repository.CategoryProductRepository;
import com.database.migration.repository.ProductRepository;
import com.database.migration.util.ModelMapperUtility;
import com.database.migration.util.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Autowired
    private ModelMapperUtility mapperUtility;

    @Override
    public Page<ListProductDto> findAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        return mapperListProductToDto(products, pageable);
    }

    @Override
    public DetailProductDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + id));

        return mapperEntityDetailProductToDto(product);
    }

    @Override
    public Product createNewProducts(CreateProductDto payload) {
        return mapperDtoCreateProductToEntity(payload);
    }

    @Override
    public Product updateProduct(Long id, Product payload) {
        Product productById = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + id));

        productById.setName(payload.getName());
        productById.setQty(payload.getQty());
        productById.setPrice(payload.getPrice());

        productRepository.save(productById);

        return productById;
    }

    @Override
    public void deleteProduct(Long id) {
        Product productById = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + id));

        productRepository.delete(productById);
    }

    private Page<ListProductDto> mapperListProductToDto(Page<Product> products, Pageable pageable) {
        List<ListProductDto> listProductDtoList = new ArrayList<>();

        if(products.getSize() != 0) {
            for (Product product: products) {
                CategoryProduct categoryProductById = categoryProductRepository
                        .findById(product.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + product.getCategoryId()));

                ListProductDto listProductDto = ListProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .categoryName(categoryProductById.getName())
                        .build();

                listProductDtoList.add(listProductDto);
            }
        }

        return new PageImpl<>(listProductDtoList, pageable, listProductDtoList.size());
    }

    private Product mapperDtoCreateProductToEntity(CreateProductDto createProductDto) {
        Product product = null;

        if (createProductDto.getCategoryId() != null) {
            product = mapperUtility
                    .modelMapperUtil()
                    .map(createProductDto, Product.class);
        }

        CategoryProduct categoryProduct = categoryProductRepository
                .findById(createProductDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Data not found with id : "
                        + createProductDto.getCategoryId()));

        product.setId(null);
        product.setName(createProductDto.getName());
        product.setQty(createProductDto.getQty());
        product.setPrice(createProductDto.getPrice());
        product.setCategoryId(categoryProduct.getId());

        return productRepository.save(product);
    }

    private DetailProductDto mapperEntityDetailProductToDto(Product product) {
        CategoryProduct categoryProduct = categoryProductRepository
                .findById(product.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + product.getCategoryId()));

        DetailProductDto detailProductDto = DetailProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .qty(product.getQty())
                .categoryName(categoryProduct.getName())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

        return detailProductDto;
    }
}
