package com.burger.delight.burgerdelightbackend2.service.impl;

import com.burger.delight.burgerdelightbackend2.dto.OrderDTO;
import com.burger.delight.burgerdelightbackend2.model.Orders;
import com.burger.delight.burgerdelightbackend2.repo.OrderRepo;
import com.burger.delight.burgerdelightbackend2.service.OrderService;
import com.burger.delight.burgerdelightbackend2.util.ResponseList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: ruu
 * Created: 2024-01-23 11.28
 */

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;

    @Override
    public String addOrder(OrderDTO order) {
        try{
            if(orderRepo.existsById(order.getId())) {
                return ResponseList.RSP_DUPLICATE;
            }else {
                orderRepo.save(modelMapper.map(order, Orders.class));
                return ResponseList.RSP_SUCCESS;
            }
        }catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public String updateOrder(OrderDTO order) {
        try {
            if(!orderRepo.existsById(order.getId())) {
                return ResponseList.RSP_NOT_FOUND;
            }
            orderRepo.save(modelMapper.map(order, Orders.class));
            return ResponseList.RSP_SUCCESS;
        }catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public String deleteOrder(int id) {
        try {
            if(!orderRepo.existsById(id)) {
                return ResponseList.RSP_NOT_FOUND;
            }
            orderRepo.deleteById(id);
            return ResponseList.RSP_SUCCESS;
        }catch (Exception e) {
            return ResponseList.RSP_FAILURE;
        }
    }

    @Override
    public OrderDTO getOrder(int id) {
        try{
            if(!orderRepo.existsById(id)) {
                return null;
            }
            Orders orders = orderRepo.findById(id).get();
            return modelMapper.map(orders, OrderDTO.class);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public OrderDTO[] getAllOrders() {
        try {
            return modelMapper.map(orderRepo.findAll(), OrderDTO[].class);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getNewOrderId() {
        try{
            return orderRepo.findAll().size() + 1;
        }catch (Exception e) {
            return 0;
        }
    }
}
