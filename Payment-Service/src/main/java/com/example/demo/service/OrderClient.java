package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.OrderStatusUpdateRequestDTO;

@Component
public class OrderClient {
	
	@Autowired
	 private  RestTemplate  rt;


	    public void updateOrderStatus(String orderId, String status) {
	        String url = "http://localhost:8081/order/" + orderId + "/status?status=" + status;
	        
	        OrderStatusUpdateRequestDTO request = new OrderStatusUpdateRequestDTO(orderId, status);
	        
	        String response  = rt.patchForObject(url, request, String.class);
	        System.out.println("Order status updated: " + response);
	    }
}
