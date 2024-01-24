package com.burger.delight.burgerdelightbackend2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: ruu
 * Created: 2024-01-23 11.12
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Food {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private String category;
}

