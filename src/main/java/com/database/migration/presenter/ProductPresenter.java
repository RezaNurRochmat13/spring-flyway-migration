package com.database.migration.presenter;

import com.database.migration.entity.Product;
import com.database.migration.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProductPresenter {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/products")
    public Map<String, Object> allProducts() {
        Map<String, Object> map = new HashMap<>();
        List<Product> productList = productService.findAllProducts();

        map.put("data", productList);

        return map;
    }
}
