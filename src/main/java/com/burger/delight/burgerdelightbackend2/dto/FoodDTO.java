package com.burger.delight.burgerdelightbackend2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ruu
 * Created: 2024-01-23 11.10
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodDTO {
    private int id;
    private String name;
    private String description;
    private String price;
    private String image;
    private String category;
}
