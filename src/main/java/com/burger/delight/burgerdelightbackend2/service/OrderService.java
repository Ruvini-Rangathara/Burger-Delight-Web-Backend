package com.burger.delight.burgerdelightbackend2.service;

import com.burger.delight.burgerdelightbackend2.dto.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * Author: ruu
 * Created: 2024-01-23 11.28
 */

public interface OrderService {
    String addOrder(OrderDTO order);
    String updateOrder(OrderDTO order);
    String deleteOrder(int id);
    OrderDTO getOrder(int id);
    OrderDTO[] getAllOrders();
    int getNewOrderId();
    OrderDTO[] getOrdersByCustomerId(int id);
    OrderDTO[] getOrdersByStatusAndCustomerId(String status, int customerId);
    boolean existsById(int id);
}
