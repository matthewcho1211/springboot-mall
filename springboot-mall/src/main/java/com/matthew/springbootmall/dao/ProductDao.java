package com.matthew.springbootmall.dao;

import com.matthew.springbootmall.constant.ProductCategory;
import com.matthew.springbootmall.dto.ProductQueryParams;
import com.matthew.springbootmall.dto.ProductRequest;
import com.matthew.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
