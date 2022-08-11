package com.database.migration.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ListResponseDtoTest {
    private ListResponse listResponse;

    private MetaResponse metaResponse;

    @Test
    public void testGetterAccessorMethod() {
        metaResponse = MetaResponse.builder()
                .count(10)
                .total(10L)
                .currentPage(1)
                .page(1)
                .build();

        listResponse = ListResponse.builder()
                .data("Test object")
                .metaResponse(metaResponse)
                .build();

        assertEquals("Test object", listResponse.getData());
        assertEquals(10, metaResponse.getCount());
        assertEquals(10L, metaResponse.getTotal());
        assertEquals(1, metaResponse.getPage());
        assertEquals(1, metaResponse.getCurrentPage());
        assertEquals(listResponse.getMetaResponse(), metaResponse);
    }

    @Test
    public void testSetterAccessorMethod() {

        metaResponse = MetaResponse.builder()
                .count(10)
                .total(10L)
                .currentPage(1)
                .page(1)
                .build();

        listResponse = ListResponse.builder()
                .data("Test object")
                .metaResponse(metaResponse)
                .build();

        metaResponse.setCount(1);
        metaResponse.setPage(1);
        metaResponse.setTotal(10L);
        metaResponse.setCurrentPage(1);

        listResponse.setData("Test object");
        listResponse.setMetaResponse(metaResponse);

        assertEquals("Test object", listResponse.getData());
        assertEquals(1, metaResponse.getCount());
        assertEquals(10L, metaResponse.getTotal());
        assertEquals(1, metaResponse.getPage());
        assertEquals(1, metaResponse.getCurrentPage());
        assertEquals(listResponse.getMetaResponse(), metaResponse);
    }

}