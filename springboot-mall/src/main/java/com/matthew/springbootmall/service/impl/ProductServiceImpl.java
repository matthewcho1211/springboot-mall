package com.matthew.springbootmall.service.impl;

import com.matthew.springbootmall.dao.ProductDao;
import com.matthew.springbootmall.dto.ProductRequest;
import com.matthew.springbootmall.model.Product;
import com.matthew.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(){
        return productDao.getProducts();
    }
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId){
        productDao.deleteProductById(productId);
    }


}
