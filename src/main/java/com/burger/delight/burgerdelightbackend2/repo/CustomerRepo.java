package com.burger.delight.burgerdelightbackend2.repo;

import com.burger.delight.burgerdelightbackend2.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: ruu
 * Created: 2024-01-23 11.23
 */

@Repository
public interface CustomerRepo extends MongoRepository<Customer, String> {
}
