package com.database.migration.configuration;

import com.database.migration.entity.Product;
import com.database.migration.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductDataLoader implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Override
    @EventListener
    public void run(String... args) throws Exception {
        List<Product> productList = Arrays.asList(
                new Product("Astra", "200000", 20),
                new Product("Honda", "100000", 10)
        );
    }
}
