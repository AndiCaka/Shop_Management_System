package com.example.shop_mng_system.service.serviceImpl;

import com.example.shop_mng_system.entity.Category;
import com.example.shop_mng_system.entity.Product;
import com.example.shop_mng_system.exception.ResourceNotFoundException;
import com.example.shop_mng_system.repository.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product addProduct(Product product, Long categoryId) {
        // Fetch the category from the database
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));

        // Set the category for the product
        product.setCategory(category);

        // Save the product to the database
        return productRepository.save(product);
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
            existingProduct.setCategory(product.getCategory());

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

    @Override
    public List<Product> getAllProductsByCategory(Long categoryId) {
        // Fetch all products associated with the given category
        return productRepository.findByCategoryId(categoryId);
    }
}

