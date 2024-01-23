package com.burger.delight.burgerdelightbackend2.repo;

import com.burger.delight.burgerdelightbackend2.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author: ruu
 * Created: 2024-01-23 11.25
 */

public interface OrderRepo extends MongoRepository<Orders, String> {
}
