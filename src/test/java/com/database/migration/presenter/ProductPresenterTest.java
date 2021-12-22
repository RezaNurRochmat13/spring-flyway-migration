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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductPresenter.class)
@ContextConfiguration(classes = ProductPresenter.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductPresenterTest {
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
        productRepository.save(new Product("Manuk", "20000", 10));
    }

    @Test
    public void getAllProducts() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        System.out.println(response.getResponse().getContentAsString());
    }
}