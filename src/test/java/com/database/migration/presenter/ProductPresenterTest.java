package com.database.migration.presenter;

import com.database.migration.repository.ProductRepository;
import com.database.migration.service.ProductServiceImpl;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductPresenterTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Flyway flyway;

    @MockBean
    private ProductServiceImpl productService;

    @MockBean
    private ProductPresenter productPresenter;

    @Before
    public void setUp() {
        flyway.migrate();
    }


    @Test
    public void getAllProducts() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals(String.valueOf(MediaType.APPLICATION_JSON), response.getResponse().getContentType());
//        assertEquals(2, response.getResponse().getContentLength());
        System.out.println(response.getResponse().getContentLength());
        System.out.println(productRepository.count());
    }
}