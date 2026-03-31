package com.example.demo.service;

import java.time.LocalDateTime;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PaymentRequestDTO;
import com.example.demo.dto.PaymentResponseDTO;
import com.example.demo.dto.PaymentStatus;
import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private OrderClient client;
	@Autowired
	private PaymentRepository repo;
	
	
	
	public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO) {
		
        String paymentId = generatePaymentId();
        
        Payment payment = new Payment();
        
        payment.setPaymentId(paymentId);
        payment.setOrderId(paymentRequestDTO.getOrderId());
        payment.setCustomerId(paymentRequestDTO.getCustomerId());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        
        boolean paymentSuccess = new Random().nextBoolean();
        
        if(paymentSuccess) {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setTransactionId(UUID.randomUUID().toString());
            client.updateOrderStatus(paymentRequestDTO.getOrderId(), "CONFIRMED");
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            payment.setTransactionId("N/A");
            client.updateOrderStatus(paymentRequestDTO.getOrderId(), "CANCELLED");
        }
        repo.save(payment);
        
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setPaymentId(paymentId);
        paymentResponseDTO.setOrderId(payment.getOrderId());
        paymentResponseDTO.setCustomerId(payment.getCustomerId());
        paymentResponseDTO.setAmount(payment.getAmount());
        paymentResponseDTO.setPaymentStatus(payment.getPaymentStatus());
        paymentResponseDTO.setPaymentDate(LocalDateTime.now());
        paymentResponseDTO.setTransactionId(payment.getTransactionId());
        return paymentResponseDTO;

    }

    public PaymentResponseDTO getPaymentByOrderId(String orderId) {
        Payment payment = repo.findByOrderId(orderId);
        if (payment == null) {
            return null; // or throw an exception
        }
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setPaymentId(payment.getPaymentId());
        paymentResponseDTO.setOrderId(payment.getOrderId());
        paymentResponseDTO.setAmount(payment.getAmount());
        paymentResponseDTO.setPaymentStatus(payment.getPaymentStatus());
        paymentResponseDTO.setTransactionId(payment.getTransactionId());
        paymentResponseDTO.setCustomerId(payment.getCustomerId());
        paymentResponseDTO.setPaymentDate(payment.getPaymentDate());
        return paymentResponseDTO;
    }
    private String generatePaymentId() {
        return "pay-" + UUID.randomUUID().toString().substring(0, 8);
    }

}
