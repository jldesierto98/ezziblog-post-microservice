package com.eeezi.ezziblogrestapi.category.service;

import com.eeezi.ezziblogrestapi.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    /**
     *
     * @param categoryDto
     * @return the saved Category (CategoryDto)
     */
    CategoryDto addCategory(CategoryDto categoryDto);

    /**
     * Gets the category by ID.
     *
     * @param categoryId
     * @return CategoryDto
     */
    CategoryDto getCategoryById(Long categoryId);

    /**
     * Gets all recorded categories in the database.
     *
     * @return List of Categories (CategoryDto)
     */
    List<CategoryDto> getAllCategories();

    /**
     *
     * @param categoryDto
     * @return the update category (CategoryDto)
     */
    CategoryDto updateCategory(CategoryDto categoryDto);

    /**
     * Deletes a specific category by its ID.
     *
     * @param categoryId
     * @return Delete message (String)
     */
    String deleteCategory(Long categoryId);



}
