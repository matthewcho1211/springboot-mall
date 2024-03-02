package com.matthew.springbootmall.dao;

import com.matthew.springbootmall.dto.OrderQueryParams;
import com.matthew.springbootmall.model.Order;
import com.matthew.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
