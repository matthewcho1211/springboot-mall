package com.matthew.springbootmall.service;

import com.matthew.springbootmall.controller.OrderController;
import com.matthew.springbootmall.dto.CreateOrderRequest;
import com.matthew.springbootmall.dto.OrderQueryParams;
import com.matthew.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(Integer userId, OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
