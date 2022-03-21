package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.entity.Product;
import com.database.migration.entity.dto.ListProductDto;
import com.database.migration.repository.CategoryProductRepository;
import com.database.migration.repository.ProductRepository;
import com.database.migration.util.ModelMapperUtility;
import com.database.migration.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
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

        return mapperProductToDto(products, pageable);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + id));
    }

    @Override
    public Product createNewProducts(Product payload) {
        return productRepository.save(payload);
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

    private Page<ListProductDto> mapperProductToDto(Page<Product> products, Pageable pageable) {
        List<ListProductDto> listProductDtoList = new ArrayList<>();

        for (Product product: products) {
            ListProductDto listProductDto = mapperUtility
                    .modelMapperUtil()
                    .map(product, ListProductDto.class);
            CategoryProduct categoryProductById = categoryProductRepository
                    .findById(product.getCategoryProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + product.getCategoryProductId()));

            listProductDto.setId(product.getId());
            listProductDto.setName(product.getName());
            listProductDto.setPrice(product.getPrice());
            listProductDto.setCategoryName(categoryProductById.getName());

            listProductDtoList.add(listProductDto);
        }

        return new PageImpl<ListProductDto>(listProductDtoList, pageable, listProductDtoList.size());
    }
}
