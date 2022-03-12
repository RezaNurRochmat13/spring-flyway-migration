package com.database.migration.presenter;

import com.database.migration.entity.CategoryProduct;
import com.database.migration.repository.CategoryProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryProductPresenter.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryProductPresenterTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryProductRepository categoryProductRepo;

    @MockBean
    private CategoryProductPresenter categoryProductPresenter;

    CategoryProduct CATEGORY_1 = new CategoryProduct(1L, "Sembako", "Sembako");
    CategoryProduct CATEGORY_2 = new CategoryProduct(2L, "Alat tulis", "Alat tulis");
    CategoryProduct CATEGORY_3 = new CategoryProduct(3L, "Alat sawah", "Alat sawah");


    @Test
    public void getAllCategoriesWithPagination() throws Exception {
        List<CategoryProduct> categoryRecords = new ArrayList<>(Arrays.asList(CATEGORY_1, CATEGORY_2, CATEGORY_3));

        Mockito.when(categoryProductRepo.findAll()).thenReturn(categoryRecords);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/categories?page=0&size=10")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

    @Test
    public void getAllCategoriesWithoutPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/categories")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    public void getCategoriesByIdWithInvalidId() throws Exception {
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
}