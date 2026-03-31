package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.dto.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Payment {
	 @Id
	    private String paymentId;
	    private String orderId;
	    private String customerId;
	    private Double amount;
	    private LocalDateTime paymentDate;
	    private PaymentStatus paymentStatus;
	    private String transactionId;
}
