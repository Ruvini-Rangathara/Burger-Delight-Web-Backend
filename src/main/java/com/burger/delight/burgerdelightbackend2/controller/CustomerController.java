package com.burger.delight.burgerdelightbackend2.controller;

import com.burger.delight.burgerdelightbackend2.dto.CustomerDTO;
import com.burger.delight.burgerdelightbackend2.dto.ResponseDTO;
import com.burger.delight.burgerdelightbackend2.service.CustomerService;
import com.burger.delight.burgerdelightbackend2.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: ruu
 * Created: 2024-01-23 12.40
 */

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping(value = "/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        String result = customerService.addCustomer(customerDTO);

        if (result.equals("00")) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Customer saved successfully");
            responseDTO.setContent(customerDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else if (result.equals("03")) {
            responseDTO.setCode(ResponseList.RSP_DUPLICATE);
            responseDTO.setMessage("Customer already exists");
            responseDTO.setContent(customerDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

        } else {
            responseDTO.setCode(ResponseList.RSP_FAILURE);
            responseDTO.setMessage("Failed to save customer");
            responseDTO.setContent(customerDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        String result = customerService.updateCustomer(customerDTO);

        if (result.equals("00")) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Customer updated successfully");
            responseDTO.setContent(customerDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else if (result.equals("04")) {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Customer not found");
            responseDTO.setContent(customerDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

        } else {
            responseDTO.setCode(ResponseList.RSP_FAILURE);
            responseDTO.setMessage("Failed to update customer");
            responseDTO.setContent(customerDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable int id) {
        String result = customerService.deleteCustomer(id);

        if (result.equals("00")) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Customer deleted successfully");
            responseDTO.setContent(id);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else if (result.equals("04")) {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Customer not found");
            responseDTO.setContent(id);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

        } else {
            responseDTO.setCode(ResponseList.RSP_FAILURE);
            responseDTO.setMessage("Failed to delete customer");
            responseDTO.setContent(id);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getCustomer(@PathVariable int id) {
        CustomerDTO customerDTO = customerService.getCustomer(id);

        if (customerDTO != null) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Customer retrieved successfully");
            responseDTO.setContent(customerDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Customer not found");
            responseDTO.setContent(id);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllCustomers() {
        CustomerDTO[] customerDTOs = customerService.getAllCustomers();

        if (customerDTOs != null) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Customers retrieved successfully");
            responseDTO.setContent(customerDTOs);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Customers not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getNewId")
    public ResponseEntity<ResponseDTO> getNewId() {
        int id = customerService.getNewCustomerId();
        responseDTO.setCode(ResponseList.RSP_SUCCESS);
        responseDTO.setMessage("New id retrieved successfully");
        responseDTO.setContent(id);
        return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);
    }




}