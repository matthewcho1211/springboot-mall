package com.matthew.springbootmall.dao;

import com.matthew.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
