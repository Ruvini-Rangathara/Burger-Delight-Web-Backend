package com.burger.delight.burgerdelightbackend2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: ruu
 * Created: 2024-01-23 11.13
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private int id;
    private String date;
    private Double total;
    private int customerId;
    private String status;
    private List<OrderedFoodDTO> list = new ArrayList<>();
}
