package com.database.migration.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@Getter
@Setter
public class SingleResponse {
    private Object data;
}
