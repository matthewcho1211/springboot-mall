package com.matthew.springbootmall.service.impl;

import com.matthew.springbootmall.dao.OrderDao;
import com.matthew.springbootmall.dao.ProductDao;
import com.matthew.springbootmall.dao.UserDao;
import com.matthew.springbootmall.dto.BuyItem;
import com.matthew.springbootmall.dto.CreateOrderRequest;
import com.matthew.springbootmall.dto.OrderQueryParams;
import com.matthew.springbootmall.model.Order;
import com.matthew.springbootmall.model.OrderItem;
import com.matthew.springbootmall.model.Product;
import com.matthew.springbootmall.model.User;
import com.matthew.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;


    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(Integer userId, OrderQueryParams orderQueryParams) {
        User user = userDao.getUserById(userId);
        if(user == null){
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order : orderList){

            List<OrderItem> orderItemList =  orderDao.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }

        return orderList;

    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        User user = userDao.getUserById(userId);
        if(user == null){
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查product 是否存在，庫存是否足夠
            if (product == null){
                log.warn("該商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            } else if (product.getStock() < buyItem.getQuantity()) {

                log.warn("該商品 {} 存數量不足，無法購買。剩餘庫存為 {}，欲購買數量 {} ",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //成功購買product並扣除商品庫存
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

            //計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }
        Integer orderId = orderDao.createOrder(userId, totalAmount);
        orderDao.createOrderItems(orderId, orderItemList);
        return orderId;
    }
}
