 package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/prod")
public class ProductController {
	 private ProductService productService;

	    public ProductController(ProductService productService) {
	        this.productService = productService;
	    }
	    
	    @PostMapping
	    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
	        return productService.createProduct(productRequestDto);
	    }
	    @GetMapping("/{productId}")
	    public ProductResponseDto getProductById(@PathVariable String productId) {
	        return productService.getProductById(productId);
	    }
	    @GetMapping
	    public List<ProductResponseDto> getAllProducts() {
	        return productService.getAllProducts();
	    }
	    
	    @PatchMapping("/{productId}/stock")
	    public ProductResponseDto updateStock(@PathVariable String productId, @RequestParam Integer stockQuantity) {
	        return productService.updateStock(productId, stockQuantity);
	    }
	    
	    @DeleteMapping("/{productId}")
	    public String deleteProduct(@PathVariable String productId) {
	        return productService.deleteProduct(productId);

	    }
}
