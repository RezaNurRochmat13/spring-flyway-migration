package com.database.migration.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class MetaResponse {
    private Integer page;
    private Integer count;
    private Long total;
    private Integer currentPage;
}
