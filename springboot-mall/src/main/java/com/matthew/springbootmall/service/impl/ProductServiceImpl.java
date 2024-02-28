package com.matthew.springbootmall.service.impl;

import com.matthew.springbootmall.dao.ProductDao;
import com.matthew.springbootmall.model.Product;
import com.matthew.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
