package com.example.shop_mng_system.service;

import com.example.shop_mng_system.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    Product getProduct(long productId);

    List<Product> getAllProducts();

    Product updateProduct(Long productId, Product product);

    void deleteProduct(long productId);
}
