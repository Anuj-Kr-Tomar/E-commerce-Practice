package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedCategoryResponseDto extends CategoryResponseDto {
	
	 List<ProductResponseDto> products;

	 public ExtendedCategoryResponseDto(String categoryId, String name, String description, List<ProductResponseDto> products) {
		super(categoryId, name, description);
		this.products = products;
	 }
  
}
