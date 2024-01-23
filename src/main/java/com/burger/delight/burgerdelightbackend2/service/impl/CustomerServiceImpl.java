package com.burger.delight.burgerdelightbackend2.service.impl;

import com.burger.delight.burgerdelightbackend2.dto.CustomerDTO;
import com.burger.delight.burgerdelightbackend2.model.Customer;
import com.burger.delight.burgerdelightbackend2.repo.CustomerRepo;
import com.burger.delight.burgerdelightbackend2.service.CustomerService;
import com.burger.delight.burgerdelightbackend2.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: ruu
 * Created: 2024-01-23 11.27
 */

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;

    @Override
    public String addCustomer(CustomerDTO customer) {
        try {
            if(customerRepo.existsById(String.valueOf(customer.getId()))){
                return ResponseList.RSP_DUPLICATE;
            }
            customerRepo.save(modelMapper.map(customer, Customer.class));
            return ResponseList.RSP_SUCCESS;
        } catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public String updateCustomer(CustomerDTO customer) {
        try{
            if( !(customerRepo.existsById(String.valueOf(customer.getId()))) ){
                return ResponseList.RSP_NOT_FOUND;
            }
            customerRepo.save(modelMapper.map(customer, Customer.class));
            return ResponseList.RSP_SUCCESS;
        } catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public String deleteCustomer(int id) {
        try{
            if( !(customerRepo.existsById(String.valueOf(id))) ){
                return ResponseList.RSP_NOT_FOUND;
            }
            customerRepo.deleteById(String.valueOf(id));
            return ResponseList.RSP_SUCCESS;
        } catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public CustomerDTO getCustomer(int id) {
        try {
            if( !(customerRepo.existsById(String.valueOf(id))) ){
                return null;
            }
            return modelMapper.map(customerRepo.findById(String.valueOf(id)).get(), CustomerDTO.class);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public CustomerDTO[] getAllCustomers() {
        try{
            return modelMapper.map(customerRepo.findAll(), CustomerDTO[].class);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getNewCustomerId() {
        try {
            return customerRepo.findAll().size() + 1;
        }
        catch (Exception e) {
            return 0;
        }
    }
}
