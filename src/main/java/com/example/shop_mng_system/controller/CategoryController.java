package com.example.shop_mng_system.controller;

import com.example.shop_mng_system.entity.Bill;
import com.example.shop_mng_system.entity.Category;
import com.example.shop_mng_system.exception.ResourceNotFoundException;
import com.example.shop_mng_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Endpoint to add a new category.
     *
     * @param category The category object to be added.
     * @return ResponseEntity containing the created category.
     */
    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        try {
            Category createdCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Return 400 if there's an error
        }
    }

    /**
     * Endpoint to retrieve all categories.
     *
     * @return ResponseEntity containing a list of all categories.
     */
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * Endpoint to retrieve a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return ResponseEntity containing the retrieved category.
     */
    @GetMapping("/getCategory/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id){
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return ResponseEntity.notFound().build(); // Return 404 if category is not found
        }
        return ResponseEntity.ok(category);
    }

    /**
     * Endpoint to update a category.
     *
     * @param id       The ID of the category to update.
     * @param category The updated category object.
     * @return ResponseEntity containing the updated category.
     */
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
        try {
            Category updatedCategory = categoryService.updateCategory(id, category);
            return ResponseEntity.ok(updatedCategory);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build(); // Return 404 if category not found
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Return 400 for other errors
        }
    }

    /**
     * Endpoint to delete a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return ResponseEntity indicating the result of the operation.
     */
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Return 204 if category is successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if category is not found
        }
    }
}
