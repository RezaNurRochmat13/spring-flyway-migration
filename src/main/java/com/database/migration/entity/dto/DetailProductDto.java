package com.database.migration.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class DetailProductDto implements Serializable {
    private Date createdAt;
    private Date updatedAt;
    private Long id;
    private String name;
    private String price;
    private Integer qty;
    @JsonProperty("category_name")
    private String categoryName;
}
