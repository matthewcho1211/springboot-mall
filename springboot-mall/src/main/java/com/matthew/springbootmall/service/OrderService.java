package com.matthew.springbootmall.service;

import com.matthew.springbootmall.dto.CreateOrderRequest;
import com.matthew.springbootmall.model.Order;

public interface OrderService {
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
