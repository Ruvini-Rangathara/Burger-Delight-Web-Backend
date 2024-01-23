package com.burger.delight.burgerdelightbackend2.repo;

import com.burger.delight.burgerdelightbackend2.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author: ruu
 * Created: 2024-01-23 11.24
 */

public interface FoodRepo extends MongoRepository<Food, String> {
}
