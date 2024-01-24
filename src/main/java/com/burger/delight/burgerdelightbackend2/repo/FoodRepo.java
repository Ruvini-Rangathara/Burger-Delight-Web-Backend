package com.burger.delight.burgerdelightbackend2.repo;

import com.burger.delight.burgerdelightbackend2.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: ruu
 * Created: 2024-01-23 11.24
 */

@Repository
public interface FoodRepo extends MongoRepository<Food, Integer> {
    @Query(value = "{'category': ?1}")
    List<Food> findByCategory(String category);
}
