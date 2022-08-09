package com.database.migration.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CreateProductDto implements Serializable {
    private String name;
    private String price;
    private Integer qty;
    @JsonProperty("category_id")
    private Long categoryId;
}
