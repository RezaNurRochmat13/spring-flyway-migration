package com.database.migration.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreateProductDto implements Serializable {
    private String name;
    private String price;
    private Integer qty;
    @JsonProperty("category_id")
    private Long categoryId;
}
