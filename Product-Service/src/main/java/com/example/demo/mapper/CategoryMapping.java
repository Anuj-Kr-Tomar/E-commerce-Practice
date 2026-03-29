package com.example.demo.mapper;

import com.example.demo.dto.CategoryResponseDto;
import com.example.demo.entity.CategoryEntity;

public class CategoryMapping {
		
	 public static CategoryResponseDto toCategoryResponseDto(CategoryEntity category) {
	        CategoryResponseDto responseDto = new CategoryResponseDto();
	        responseDto.setCategoryId(category.getCategoryId());
	        responseDto.setName(category.getName());
	        responseDto.setDescription(category.getDescription());
	        return responseDto;
	    }
	 
	    public static CategoryEntity toCategoryEntity(CategoryResponseDto categoryResponseDto) {
	        CategoryEntity category = new CategoryEntity();
	        category.setCategoryId(categoryResponseDto.getCategoryId());
	        category.setName(categoryResponseDto.getName());
	        category.setDescription(categoryResponseDto.getDescription());
	        return category;
	    }
	    
}
