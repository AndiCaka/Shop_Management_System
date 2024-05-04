package com.example.shop_mng_system.service.serviceImpl;

import com.example.shop_mng_system.entity.Product;
import com.example.shop_mng_system.repository.ProductRepository;
import com.example.shop_mng_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PtoductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProduct(long productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
            Product existingProduct = productRepository.findById(productId).get();

            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());

            return productRepository.save(existingProduct);
    }

    @Override
    public boolean deleteProduct(long productId) {
        try {
            productRepository.deleteById(productId);
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            // Log the exception or handle it gracefully
            return false; // Return false if an error occurs during deletion
        }
    }
}

