package com.burger.delight.burgerdelightbackend2.controller;

import com.burger.delight.burgerdelightbackend2.dto.OrderDTO;
import com.burger.delight.burgerdelightbackend2.dto.ResponseDTO;
import com.burger.delight.burgerdelightbackend2.service.OrderService;
import com.burger.delight.burgerdelightbackend2.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Author: ruu
 * Created: 2024-01-24 20.57
 */

@RestController
@RequestMapping(value = "/api/v1/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveOrder(@RequestBody OrderDTO orderDTO) {

        boolean b = orderService.existsById(orderDTO.getId());
        if(b){
            String result = orderService.updateOrder(orderDTO);
            if (result.equals("00")) {
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setMessage("Order updated successfully");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

            } else if (result.equals("03")) {
                responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
                responseDTO.setMessage("Order not found");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

            } else {
                responseDTO.setCode(ResponseList.RSP_FAILURE);
                responseDTO.setMessage("Failed to update order");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
            }
        }else{
            String result = orderService.addOrder(orderDTO);
            if (result.equals("00")) {
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setMessage("Order saved successfully");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

            } else if (result.equals("03")) {
                responseDTO.setCode(ResponseList.RSP_DUPLICATE);
                responseDTO.setMessage("Order already add to cart!");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

            } else {
                responseDTO.setCode(ResponseList.RSP_FAILURE);
                responseDTO.setMessage("Failed to save order");
                responseDTO.setContent(orderDTO);
                return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
            }
        }




    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateOrder(@RequestBody OrderDTO orderDTO) {
        System.out.println("order update method called");
        String result = orderService.updateOrder(orderDTO);

        System.out.println("result : "+result);

        if (result.equals("00")) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Order updated successfully");
            responseDTO.setContent(orderDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else if (result.equals("03")) {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Order not found");
            responseDTO.setContent(orderDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

        } else {
            responseDTO.setCode(ResponseList.RSP_FAILURE);
            responseDTO.setMessage("Failed to update order");
            responseDTO.setContent(orderDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOrder(@PathVariable int id) {
        String result = orderService.deleteOrder(id);

        if (result.equals("00")) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Order deleted successfully");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else if (result.equals("03")) {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Order not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);

        } else {
            responseDTO.setCode(ResponseList.RSP_FAILURE);
            responseDTO.setMessage("Failed to delete order");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getOrder(@PathVariable int id) {
        OrderDTO orderDTO = orderService.getOrder(id);

        if (orderDTO != null) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Order retrieved successfully");
            responseDTO.setContent(orderDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Order not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByCustomerId/{id}")
    public ResponseEntity<ResponseDTO> getOrderByCustomerId(@PathVariable int id) {
        OrderDTO[] orderDTO = orderService.getOrdersByCustomerId(id);

        if (orderDTO != null) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Order retrieved successfully");
            responseDTO.setContent(orderDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Order not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getNewId")
    public ResponseEntity<ResponseDTO> getNewOrderId() {
        int id = orderService.getNewOrderId();

        if (id != 0) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Order id retrieved successfully");
            responseDTO.setContent(id);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Order id not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        OrderDTO[] orderDTO = orderService.getAllOrders();

        if (orderDTO != null) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Order retrieved successfully");
            responseDTO.setContent(orderDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Order not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }


    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/getAllByStatusAndCustomerId/{status}/{customerId}")
    public ResponseEntity<ResponseDTO> getAllOrdersByStatusAndCustomerId(@PathVariable String status, @PathVariable int customerId) {
        OrderDTO[] orderDTO = orderService.getOrdersByStatusAndCustomerId(status, customerId);

        if (orderDTO != null) {
            responseDTO.setCode(ResponseList.RSP_SUCCESS);
            responseDTO.setMessage("Order retrieved successfully");
            responseDTO.setContent(orderDTO);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.OK);

        } else {
            responseDTO.setCode(ResponseList.RSP_NOT_FOUND);
            responseDTO.setMessage("Order not found");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }
}
