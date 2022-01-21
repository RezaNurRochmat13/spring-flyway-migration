package com.database.migration.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class ListResponse {
    private List<Object> data;
    private MetaResponse metaResponse;

}
