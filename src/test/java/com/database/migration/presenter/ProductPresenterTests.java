package com.database.migration.presenter;

import com.database.migration.MigrationApplicationTests;
import com.database.migration.entity.Product;
import com.database.migration.repository.ProductRepository;
import com.database.migration.service.ProductServiceImpl;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductPresenterTests extends MigrationApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
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
    public void testGetAllProductsWithoutPagination() throws Exception {
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
    public void testGetAllProductsWithPagination() throws Exception {
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
    public void testGetSingleProductWithValidIds() throws Exception {
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

    @Test
    public void testCreateProductWithPayload() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("name", "Indomilk");
        payload.put("qty", new Random().nextInt());
        payload.put("price", "1500");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload.toJSONString());

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    public void testCreateProductWithoutPayload() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    public void testUpdateProductWithPayload() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("name", "Indomilk");
        payload.put("qty", new Random().nextInt());
        payload.put("price", "1500");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/products/" + new Random().nextLong())
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload.toJSONString());

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    public void testUpdateProductWithoutPayload() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/products/" + new Random().nextLong())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    public void testDeleteProduct() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/products/" + new Random().nextLong())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
    }

}