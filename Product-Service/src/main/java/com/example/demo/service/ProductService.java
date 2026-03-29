package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.dto.ProductResponseDto;

public interface ProductService {
	
	    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
	    
	    ProductResponseDto getProductById(String productId);
	    
	    List<ProductResponseDto> getAllProducts();
	    
	    ProductResponseDto updateStock(String productId, Integer stockQuantity);
	    
	    String deleteProduct(String productId);
}
