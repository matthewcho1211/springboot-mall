package com.matthew.springbootmall.service;

import com.matthew.springbootmall.dto.ProductRequest;
import com.matthew.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);


}
