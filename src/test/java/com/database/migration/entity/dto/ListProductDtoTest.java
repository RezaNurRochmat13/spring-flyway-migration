package com.database.migration.entity.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ListProductDtoTest {
    private ListProductDto listProductDto;

    @Test
    public void testGetterAccessor() throws Exception {
        listProductDto = ListProductDto.builder()
                .id(1L)
                .name("Susu")
                .categoryName("Sembako")
                .price("100")
                .build();

        assertEquals("Susu", listProductDto.getName());
        assertEquals("Sembako", listProductDto.getCategoryName());
        assertEquals("100", listProductDto.getPrice());
        assertEquals(1L, listProductDto.getId());

    }

    @Test
    public void testSetterAccessor() throws Exception {
        listProductDto = ListProductDto.builder()
                .id(1L)
                .name("Susu")
                .categoryName("Sembako")
                .price("100")
                .build();

        listProductDto.setId(1L);
        listProductDto.setName("Susu");
        listProductDto.setCategoryName("Sembako");
        listProductDto.setPrice("100");

        assertEquals("Susu", listProductDto.getName());
        assertEquals("Sembako", listProductDto.getCategoryName());
        assertEquals("100", listProductDto.getPrice());
        assertEquals(1L, listProductDto.getId());

    }

}