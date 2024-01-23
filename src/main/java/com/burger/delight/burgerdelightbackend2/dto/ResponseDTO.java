package com.burger.delight.burgerdelightbackend2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Author: ruu
 * Created: 2024-01-23 12.44
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ResponseDTO {
    private String code;
    private String message;
    private Object content;
}
