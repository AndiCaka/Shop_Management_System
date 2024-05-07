package com.example.shop_mng_system.controller;

import com.example.shop_mng_system.entity.Product;
import com.example.shop_mng_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Add a new product.
     *
     * @param product The product to be added.
     * @return ResponseEntity containing the added product with status code 201 (Created).
     */
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @RequestParam Long categoryId){
        Product addedProduct = productService.addProduct(product, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    /**
     * Get a product by ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return ResponseEntity containing the retrieved product with status code 200 (OK), or 404 (Not Found) if product not found.
     */
    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId){
        Product product = productService.getProduct(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all products.
     *
     * @return ResponseEntity containing a list of all products with status code 200 (OK).
     */
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Update an existing product.
     *
     * @param productId The ID of the product to be updated.
     * @param product   The updated product object.
     * @return ResponseEntity containing the updated product with status code 200 (OK), or 404 (Not Found) if product not found.
     */
    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(productId, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a product by ID.
     *
     * @param productId The ID of the product to be deleted.
     * @return ResponseEntity with status code 204 (No Content) if deletion is successful, or 404 (Not Found) if product not found.
     */
    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId){
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieve products associated with the specified category ID.
     *
     * @param categoryId The ID of the category for which products are to be retrieved.
     * @return ResponseEntity with status code 200 (OK) and the list of products if retrieval is successful,
     *         or 404 (Not Found) if the category is not found.
     */
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.getAllProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves a list of products whose names contain the specified substring.
     *
     * @param name The substring to search for in product names.
     * @return ResponseEntity with a list of products matching the search criteria and status code 200 (OK).
     */
    @GetMapping("/byName/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {
        // Call the service method to retrieve products by name containing the specified substring
        List<Product> products = productService.getAllProductsByNameContaining(name);

        // Return the list of products in the response entity with a 200 OK status
        return ResponseEntity.ok(products);
    }

}
