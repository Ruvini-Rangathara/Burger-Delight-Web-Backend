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
            if(customerRepo.existsById(customer.getId())){
                System.out.println("Customer id in duplicate : " + customer.getId());
                System.out.println("Customer already exists");
                return ResponseList.RSP_DUPLICATE;
            }else {
                System.out.println("Customer id in success : " + customer.getId());
                customerRepo.save(modelMapper.map(customer, Customer.class));
                return ResponseList.RSP_SUCCESS;
            }
        } catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }
    @Override
    public String updateCustomer(CustomerDTO customer) {
        try{
            if( !(customerRepo.existsById(customer.getId())) ){
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
            if( !(customerRepo.existsById(id) )){
                return ResponseList.RSP_NOT_FOUND;
            }
            customerRepo.deleteById(id);
            return ResponseList.RSP_SUCCESS;
        } catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public CustomerDTO getCustomer(int id) {
        try {
            if( !(customerRepo.existsById(id) )){
                return null;
            }
            return modelMapper.map(customerRepo.findById(id).get(), CustomerDTO.class);
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

    @Override
    public boolean login(String email, String password) {

        try {
            Customer customer = customerRepo.findByEmailAndPassword(email, password);
            if(customer == null){
                System.out.println("Customer not found");
                return false;
            }
            System.out.println("Customer found");
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getCustomerIdByEmail(String email) {
        try{
           Customer customer = customerRepo.findByEmail(email);
              if(customer == null){
                return 0;
              }
                return customer.getId();
        }catch (Exception e) {
            return 0;
        }
    }
}
