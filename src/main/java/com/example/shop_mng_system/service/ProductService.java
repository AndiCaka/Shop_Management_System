package com.example.shop_mng_system.service;

import com.example.shop_mng_system.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product, Long categoryId);

    Product getProduct(long productId);

    List<Product> getAllProducts();

    Product updateProduct(Long productId, Product product);

    boolean deleteProduct(long productId);

    List<Product> getAllProductsByCategory(Long categoryId);

    List<Product> getAllProductsByNameContaining(String name);
}
