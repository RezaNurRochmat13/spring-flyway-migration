package com.database.migration.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SingleResponseTest {
    private SingleResponse singleResponse;

    @Test
    public void getterAccessorTest() {
        singleResponse = SingleResponse.builder()
                .data("Test object")
                .build();

        assertEquals("Test object", singleResponse.getData());

    }

    @Test
    public void setterAccessorTest() {
        singleResponse = SingleResponse.builder()
                .data("Test object")
                .build();

        singleResponse.setData("Test object 2");

        assertEquals("Test object 2", singleResponse.getData());
    }
}