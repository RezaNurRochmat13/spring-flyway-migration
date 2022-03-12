package com.database.migration.presenter;

import com.database.migration.entity.Product;
import com.database.migration.repository.ProductRepository;
import com.database.migration.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class ProductPresenterTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductServiceImpl productService;

    @MockBean
    private ProductPresenter productPresenter;

    @Before
    public void setUp() {
        List<Product> productList = Arrays.asList(
                new Product("Indomie", "10000", 9),
                new Product("Sarimi", "5000", 6)
        );

        productRepository.saveAll(productList);
    }


    @Test
    public void getAllProductsWithoutPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    public void getAllProductsWithPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products?page=0" +
                        "&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    public void getSingleProductWithValidIds() throws Exception {
        Product product = productRepository.save(
                new Product("Indomie", "1200", 6)
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
    }

}