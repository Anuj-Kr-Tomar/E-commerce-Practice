package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
	
	  private String paymentId;
	    private String orderId;
	    private String customerId;
	    private Double amount;
	    private LocalDateTime paymentDate;
	    private PaymentStatus paymentStatus;
	    private String transactionId;
}
