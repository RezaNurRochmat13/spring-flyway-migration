package com.database.migration.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@Getter
@Setter
public class ListResponse {
    private Object data;
    private MetaResponse metaResponse;

}
