package com.database.migration.entity.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DetailProductDtoTest {
    private DetailProductDto detailProductDto;

    @Test
    public void testGetterAccessor() throws Exception {
        detailProductDto = DetailProductDto.builder()
                .id(1L)
                .name("Buku")
                .categoryName("Alat tulis")
                .price("100")
                .qty(2)
                .build();

        assertEquals(1L, detailProductDto.getId());
        assertEquals("Buku", detailProductDto.getName());
        assertEquals("Alat tulis", detailProductDto.getCategoryName());
        assertEquals("100", detailProductDto.getPrice());
        assertEquals(2, detailProductDto.getQty());
    }

    @Test
    public void testSetterAccessor() throws Exception {
        detailProductDto = DetailProductDto.builder()
                .id(1L)
                .name("Buku")
                .categoryName("Alat tulis")
                .price("100")
                .qty(2)
                .build();

        detailProductDto.setId(2L);
        detailProductDto.setName("Buku 2");
        detailProductDto.setCategoryName("Alat tulis");
        detailProductDto.setPrice("200");
        detailProductDto.setQty(3);
        detailProductDto.setCreatedAt(new Date());
        detailProductDto.setUpdatedAt(new Date());

        assertEquals(2L, detailProductDto.getId());
        assertEquals("Buku 2", detailProductDto.getName());
        assertEquals("Alat tulis", detailProductDto.getCategoryName());
        assertEquals("200", detailProductDto.getPrice());
        assertEquals(3, detailProductDto.getQty());
    }

}