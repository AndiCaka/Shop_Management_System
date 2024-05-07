package com.example.shop_mng_system.repository;

import com.example.shop_mng_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(long productId);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByNameContaining(String name);
}
