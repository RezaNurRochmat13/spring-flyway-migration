package com.database.migration.service;

import com.database.migration.entity.Product;
import com.database.migration.repository.ProductRepository;
import com.database.migration.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
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
}
