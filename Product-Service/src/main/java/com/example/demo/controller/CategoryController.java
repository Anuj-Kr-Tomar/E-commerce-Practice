package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryRequestDto;
import com.example.demo.dto.CategoryResponseDto;
import com.example.demo.dto.ExtendedCategoryResponseDto;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/cat")
public class CategoryController {
	
	
	@Autowired private CategoryService serv;
	
	@PostMapping
	public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
		CategoryResponseDto category = serv.createCategory(categoryRequestDto);
		return category;	
	}
	
	@GetMapping("/{categoryId}")
	public ExtendedCategoryResponseDto getCategoryById(@PathVariable String categoryId) {
		ExtendedCategoryResponseDto categoryById = serv.getCategoryById(categoryId);
		return categoryById;
	}
	
	@PutMapping("/{categoryId}")
    public CategoryResponseDto updateCategory(@PathVariable String categoryId, @RequestBody CategoryRequestDto categoryRequestDto) {
        return serv.updateCategory(categoryId, categoryRequestDto);
    }
	
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        serv.deleteCategory(categoryId);
    }
    
    @GetMapping
    public List<ExtendedCategoryResponseDto> getAllCategories() {
        return serv.getAllCategories();
    }
}
