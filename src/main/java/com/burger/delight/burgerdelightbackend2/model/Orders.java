package com.burger.delight.burgerdelightbackend2.model;

import com.burger.delight.burgerdelightbackend2.dto.OrderedFoodDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: ruu
 * Created: 2024-01-23 11.21
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Orders {
    private int id;
    private String date;
    private Double total;
    private int customerId;
    private String status;
    private List<OrderedFoodDTO> list = new ArrayList<>();
}