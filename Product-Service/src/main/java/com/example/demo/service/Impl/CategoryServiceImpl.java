package com.example.demo.service.Impl;

import java.util.List;

import com.example.demo.dto.CategoryRequestDto;
import com.example.demo.dto.CategoryResponseDto;
import com.example.demo.dto.ExtendedCategoryResponseDto;

public interface CategoryServiceImpl {
	
	    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
	    ExtendedCategoryResponseDto getCategoryById(String categoryId);
	    List<ExtendedCategoryResponseDto> getAllCategories();
	    CategoryResponseDto updateCategory(String categoryId, CategoryRequestDto categoryRequestDto);
	    
	    void deleteCategory(String categoryId);
}
