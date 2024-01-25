package com.burger.delight.burgerdelightbackend2.repo;

import com.burger.delight.burgerdelightbackend2.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Author: ruu
 * Created: 2024-01-23 11.25
 */

@Repository
public interface OrderRepo extends MongoRepository<Orders, Integer> {
    //get orders by customer id
    @Query("{'customerId': ?0}")
    Orders[] findByCustomerId(int id);

    //get orders by status and customer id
    @Query("{'status': ?0, 'customerId': ?1}")
    Orders[] findByStatusAndCustomerId(String status, int customerId);

}
