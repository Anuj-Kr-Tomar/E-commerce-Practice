package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryRequestDto;
import com.example.demo.dto.CategoryResponseDto;
import com.example.demo.dto.ExtendedCategoryResponseDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.mapper.CategoryMapping;
import com.example.demo.mapper.ProductMapping;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.Impl.CategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService  implements CategoryServiceImpl{
	
	private final CategoryRepository repo;

	@Override
	public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
	
		    CategoryEntity category = new CategoryEntity();
	        category.setName(categoryRequestDto.getName());
	        category.setDescription(categoryRequestDto.getDescription());
	        CategoryEntity savedCategory = repo.save(category);
	        return CategoryMapping.toCategoryResponseDto(savedCategory);

	}

	@Override
	public ExtendedCategoryResponseDto getCategoryById(String categoryId) {
		CategoryEntity category = repo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return convertToExtendedCategoryResponseDto(category);
	}

	@Override
	public List<ExtendedCategoryResponseDto> getAllCategories() 
	{
		List<CategoryEntity> categoryList = repo.findAll();
		
        List<ExtendedCategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        for (CategoryEntity category : categoryList) {
            ExtendedCategoryResponseDto categoryResponseDto = convertToExtendedCategoryResponseDto(category);
            categoryResponseDtos.add(categoryResponseDto);
        }
        return categoryResponseDtos;
	}

	@Override
	public CategoryResponseDto updateCategory(String categoryId, CategoryRequestDto categoryRequestDto) {
		CategoryEntity category = repo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        CategoryEntity updatedCategory = repo.save(category);
        return CategoryMapping.toCategoryResponseDto(updatedCategory);
	}

	@Override
	public void deleteCategory(String categoryId) {
		   CategoryEntity category = repo.findById(categoryId)
	                .orElseThrow(() -> new RuntimeException("Category not found"));
	        repo.delete(category);
		
	}
	
	private ExtendedCategoryResponseDto convertToExtendedCategoryResponseDto(CategoryEntity category) {
                List<ProductEntity> productList = category.getProducts();
                return new ExtendedCategoryResponseDto(category.getCategoryId(), category.getName(), category.getDescription(),
                productList.stream().map(ProductMapping::toProductResponseDto).toList());
    }
}
