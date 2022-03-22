package com.database.migration.service;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.entity.Product;
import com.database.migration.entity.dto.CreateProductDto;
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

        return mapperListProductToDto(products, pageable);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + id));
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

        for (Product product: products) {
            ListProductDto listProductDto = mapperUtility
                    .modelMapperUtil()
                    .map(product, ListProductDto.class);
            CategoryProduct categoryProductById = categoryProductRepository
                    .findById(product.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Data not found : " + product.getCategoryId()));

            listProductDto.setId(product.getId());
            listProductDto.setName(product.getName());
            listProductDto.setPrice(product.getPrice());
            listProductDto.setCategoryName(categoryProductById.getName());

            listProductDtoList.add(listProductDto);
        }

        return new PageImpl<ListProductDto>(listProductDtoList, pageable, listProductDtoList.size());
    }

    private Product mapperDtoCreateProductToEntity(CreateProductDto createProductDto) {
        Product product = mapperUtility
                .modelMapperUtil()
                .map(createProductDto, Product.class);

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
}
