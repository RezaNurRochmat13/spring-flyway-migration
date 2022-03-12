package com.database.migration.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
public class SingleResponse {
    private Object data;
}
