package com.eeezi.ezziblogrestapi.category.service;


import com.eeezi.ezziblogrestapi.category.dto.CategoryDto;
import com.eeezi.ezziblogrestapi.category.entity.Category;
import com.eeezi.ezziblogrestapi.category.repository.CategoryRepository;
import com.eeezi.ezziblogrestapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private static final String DELETE_MESSAGE = "Delete successful!";

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId.toString()));

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryDto.getId().toString()));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId.toString()));

        categoryRepository.deleteById(categoryId);
        return DELETE_MESSAGE;
    }
}
