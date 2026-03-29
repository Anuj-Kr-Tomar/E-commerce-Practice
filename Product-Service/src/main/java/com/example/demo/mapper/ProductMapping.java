package com.example.demo.mapper;

import com.example.demo.dto.ProductResponseDto;
import com.example.demo.entity.ProductEntity;

public class ProductMapping {
			
	 public static ProductResponseDto toProductResponseDto(ProductEntity product) {
	        ProductResponseDto responseDto = new ProductResponseDto();
	        responseDto.setProductId(product.getProductId());
	        responseDto.setName(product.getName());
	        responseDto.setDescription(product.getDescription());
	        responseDto.setPrice(product.getPrice());
	        responseDto.setStockQuantity(product.getStockQuantity());
	        responseDto.setInStock(product.getInStock());
	        responseDto.setCategoryName(product.getCategory().getName());
	        return responseDto;
	    }
	    public static ProductEntity toProductEntity(ProductResponseDto productResponseDto) {
	        ProductEntity product = new ProductEntity();
	        product.setProductId(productResponseDto.getProductId());
	        product.setName(productResponseDto.getName());
	        product.setDescription(productResponseDto.getDescription());
	        product.setPrice(productResponseDto.getPrice());
	        product.setStockQuantity(productResponseDto.getStockQuantity());
	        product.setInStock(productResponseDto.getInStock());
	        return product;
	    }
}
