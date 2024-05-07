package com.example.shop_mng_system.service;

import com.example.shop_mng_system.entity.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategory(Long id);

    Category updateCategory(Long id, Category category);

    boolean deleteCategory(Long id);

    List<Category> getAllCategoriesByNameContaining(String name);
}
