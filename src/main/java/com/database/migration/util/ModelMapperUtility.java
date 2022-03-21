package com.database.migration.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtility {
    @Bean
    public ModelMapper modelMapperUtil() {
        return new ModelMapper();
    }
}
