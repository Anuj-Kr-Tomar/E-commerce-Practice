package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {
	
	@Id
    private String orderItemId;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
}
