package com.database.migration.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ListResponse {
    private Object data;
    private MetaResponse metaResponse;

}
