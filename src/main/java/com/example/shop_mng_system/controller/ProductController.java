package com.example.shop_mng_system.controller;

import com.example.shop_mng_system.entity.Product;
import com.example.shop_mng_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return product;
    }

    @GetMapping("/getProduct/{productId}")
    public Product getProduct(@PathVariable long productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/getAllProducts")
    public List<Product> products(){
        return productService.getAllProducts();
    }

    @PutMapping("/updateProduct/{productId}")
    public Product updateProduct(@PathVariable Long productId,@RequestBody Product product){
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public void deleteProduct(@PathVariable long productId){
        productService.deleteProduct(productId);
    }
}
