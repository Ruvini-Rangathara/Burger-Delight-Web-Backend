package com.burger.delight.burgerdelightbackend2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ruu
 * Created: 2024-01-23 11.19
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderedFoodDTO {
    private int foodId;
    private int qty;
}
