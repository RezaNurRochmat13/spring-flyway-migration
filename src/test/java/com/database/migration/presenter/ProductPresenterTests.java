package com.database.migration.presenter;

import com.database.migration.MigrationApplicationTests;
import com.database.migration.entity.CategoryProduct;
import com.database.migration.entity.Product;
import com.database.migration.repository.ProductRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductPresenterTests extends MigrationApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetAllProductsWithoutPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        JSONObject jsonObject = new JSONObject(response.getResponse().getContentAsString());;

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals(4, jsonObject.getJSONArray("data").length());
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
        JSONObject jsonObject = new JSONObject(response.getResponse().getContentAsString());;

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals(4, jsonObject.getJSONArray("data").length());
    }

    @Test
    public void testGetSingleProductWithValidIds() throws Exception {
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setName("Sembako");
        categoryProduct.setDescription("Sembako");

        Product product = productRepository.save(
                new Product("Indomie", "1200", 6, categoryProduct.getId())
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
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setName("Sembako");
        categoryProduct.setDescription("Sembako");

        JSONObject payload = new JSONObject();
        payload.put("name", "Indomilk");
        payload.put("qty", new Random().nextInt());
        payload.put("price", "1500");
        payload.put("category_id", categoryProduct.getId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload.toString());

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
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setName("Sembako");
        categoryProduct.setDescription("Sembako");

        JSONObject payload = new JSONObject();
        payload.put("name", "Indomilk");
        payload.put("qty", new Random().nextInt());
        payload.put("price", "1500");
        payload.put("category_id", categoryProduct.getId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/products/" + new Random().nextLong())
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload.toString());

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