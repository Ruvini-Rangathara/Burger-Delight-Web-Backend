package com.burger.delight.burgerdelightbackend2.service;

import com.burger.delight.burgerdelightbackend2.dto.CustomerDTO;

/**
 * Author: ruu
 * Created: 2024-01-23 11.27
 */

public interface CustomerService {
    String addCustomer(CustomerDTO customer);
    String updateCustomer(CustomerDTO customer);
    String deleteCustomer(int id);
    CustomerDTO getCustomer(int id);
    CustomerDTO[] getAllCustomers();
    int getNewCustomerId();
    boolean login(String email, String password);
}
