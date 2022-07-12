package com.database.migration.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MetaResponse {
    private Integer page;
    private Integer count;
    private Long total;
    private Integer currentPage;
}
