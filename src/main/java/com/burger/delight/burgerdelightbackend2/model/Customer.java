package com.burger.delight.burgerdelightbackend2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: ruu
 * Created: 2024-01-23 11.03
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
}
