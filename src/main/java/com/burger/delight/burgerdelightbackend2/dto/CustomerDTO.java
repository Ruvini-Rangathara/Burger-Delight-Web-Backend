package com.burger.delight.burgerdelightbackend2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ruu
 * Created: 2024-01-23 11.01
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
}
