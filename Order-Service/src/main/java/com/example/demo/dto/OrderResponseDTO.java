package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
	
	private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private OrderStatus status;
    private List<OrderItem> items;
}
