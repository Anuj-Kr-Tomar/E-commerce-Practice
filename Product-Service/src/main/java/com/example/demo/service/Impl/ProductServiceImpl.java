package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepostiory;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepostiory pRepo;
	private final CategoryRepository cRepo;

	@Override
	public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
		
		  CategoryEntity category = cRepo.findById(productRequestDto.getCategoryId())
	                .orElseThrow(() -> new RuntimeException("Category not found"));
		 
		    ProductEntity product = new ProductEntity();
	        product.setName(productRequestDto.getName());
	        product.setDescription(productRequestDto.getDescription());
	        product.setPrice(productRequestDto.getPrice());
	        product.setStockQuantity(productRequestDto.getStockQuantity());
	        product.setCategory(category);
	        ProductEntity savedProduct = pRepo.save(product);
	        return toDto(savedProduct);
	        
	}

	@Override
	public ProductResponseDto getProductById(String productId) {
		ProductEntity product = pRepo.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));
	        return toDto(product);
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		  return pRepo.findAll().stream()
	                .map(this::toDto)
	                .toList();
	}

	@Override
	public ProductResponseDto updateStock(String productId, Integer stockQuantity) {
		
		    ProductEntity product = pRepo.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));
	        product.setStockQuantity(product.getStockQuantity() + stockQuantity);
	        pRepo.save(product);
	        return toDto(product);
	}

	@Override
	public String deleteProduct(String productId) {
		    ProductEntity product = pRepo.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));
	        pRepo.delete(product);
	        return "Product " + productId +" deleted successfully";
	}
	
	private ProductResponseDto toDto (ProductEntity product) {
		  ProductResponseDto productResponseDto = new ProductResponseDto();
	        productResponseDto.setProductId(product.getProductId());
	        productResponseDto.setName(product.getName());
	        productResponseDto.setDescription(product.getDescription());
	        productResponseDto.setPrice(product.getPrice());
	        productResponseDto.setStockQuantity(product.getStockQuantity());
	        productResponseDto.setInStock(product.getInStock());
	        productResponseDto.setCategoryName(product.getCategory().getName());
	        return productResponseDto;
	}

}
