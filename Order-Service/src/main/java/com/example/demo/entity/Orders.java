package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.dto.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {

	    @Id
	    private String orderId;
	    private String customerId;
	    private LocalDateTime orderDate;
	    private Double totalAmount;
	    
	    @Enumerated(EnumType.STRING)
	    private OrderStatus status;
}
