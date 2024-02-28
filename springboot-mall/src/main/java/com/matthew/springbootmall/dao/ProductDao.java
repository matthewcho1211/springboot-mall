package com.matthew.springbootmall.dao;

import com.matthew.springbootmall.constant.ProductCategory;
import com.matthew.springbootmall.dto.ProductRequest;
import com.matthew.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
