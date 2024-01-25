package com.burger.delight.burgerdelightbackend2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ruu
 * Created: 2024-01-25 21.23
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailDTO {
    private String to;
    private String subject;
    private String body;

}
