package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
	private String productId;
    private String name;
    private Double price;
    private Integer stockQuantity;
}
