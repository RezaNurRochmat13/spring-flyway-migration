package com.database.migration.presenter;

import com.database.migration.MigrationApplicationTests;
import com.database.migration.entity.CategoryProduct;
import com.database.migration.repository.CategoryProductRepository;
import org.json.JSONObject;
import org.junit.Before;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryProductPresenterTests extends MigrationApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryProductRepository categoryProductRepo;

    CategoryProduct CATEGORY_1 = new CategoryProduct(1L, "Sembako", "Sembako");
    CategoryProduct CATEGORY_2 = new CategoryProduct(2L, "Alat tulis", "Alat tulis");
    CategoryProduct CATEGORY_3 = new CategoryProduct(3L, "Alat sawah", "Alat sawah");
    CategoryProduct CATEGORY_4 = new CategoryProduct(4L, "Lain-lain", "Lain-lain");
    CategoryProduct CATEGORY_5 = new CategoryProduct(5L, "Alat sawah", "Alat sawah");

    @Before
    public void setUpData() {
        List<CategoryProduct> categoryRecords = new ArrayList<>(
                Arrays.asList(CATEGORY_1, CATEGORY_2, CATEGORY_3, CATEGORY_4, CATEGORY_5));

        categoryProductRepo.saveAll(categoryRecords);
    }

    @Test
    public void testGetAllCategoriesWithPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/categories?page=0&size=10")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        JSONObject jsonObject = new JSONObject(response.getResponse().getContentAsString());;

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals(5, jsonObject.getJSONArray("data").length());

    }

    @Test
    public void testGetAllCategoriesWithoutPagination() throws Exception {
        List<CategoryProduct> categoryRecords = new ArrayList<>(Arrays.asList(CATEGORY_1, CATEGORY_2, CATEGORY_3));

        categoryProductRepo.saveAll(categoryRecords);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/categories")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        JSONObject jsonObject = new JSONObject(response.getResponse().getContentAsString());

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals(5, jsonObject.getJSONArray("data").length());
    }

    @Test
    public void testGetCategoriesByIdWithInvalidId() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/categories/" + new Random().nextLong())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(404, response.getResponse().getStatus());
    }

    @Test
    public void testGetCategoriesByIdWithValidId() throws Exception {
        CategoryProduct categoryProduct = categoryProductRepo
                .save(new CategoryProduct(1L, "Alat tulis", ""));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/categories/" + categoryProduct.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        JSONObject jsonObject = new JSONObject(response.getResponse().getContentAsString());
        JSONObject actual = jsonObject.getJSONObject("data");

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals("Alat tulis", actual.get("name"));

    }

    @Test
    public void testCreateNewCategoriesWithValidPayload() throws Exception {}

    @Test
    public void testCreateNewCategoriesWithoutPayload() throws Exception {}
}