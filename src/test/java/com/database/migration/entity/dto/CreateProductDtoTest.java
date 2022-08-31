package com.database.migration.entity.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreateProductDtoTest {
    private CreateProductDto createProductDto;

    @Test
    public void testGetterAccessor() throws Exception {
        createProductDto = CreateProductDto.builder()
                .name("Indomilk")
                .qty(2)
                .price("1000")
                .categoryId(1L)
                .build();

        assertEquals("Indomilk", createProductDto.getName());
        assertEquals(2, createProductDto.getQty());
        assertEquals("1000", createProductDto.getPrice());
        assertEquals(1L, createProductDto.getCategoryId());
    }

    @Test
    public void testSetterAccessor() throws Exception {
        createProductDto = CreateProductDto.builder()
                .name("Indomilk")
                .qty(2)
                .price("1000")
                .categoryId(1L)
                .build();

        createProductDto.setName("Indomilk 2");
        createProductDto.setQty(3);
        createProductDto.setPrice("2000");
        createProductDto.setCategoryId(2L);

        assertEquals("Indomilk 2", createProductDto.getName());
        assertEquals(3, createProductDto.getQty());
        assertEquals("2000", createProductDto.getPrice());
        assertEquals(2L, createProductDto.getCategoryId());
    }
}