package com.database.migration.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class MetaResponse {
    private Integer page;
    private Integer count;
    private Long total;
    private Integer currentPage;
}
