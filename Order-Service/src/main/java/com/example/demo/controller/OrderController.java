package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.dto.OrderStatus;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	 private final OrderService orderService;

	    public OrderController(OrderService orderService) {
	        this.orderService = orderService;
	    }
	    
	    @PostMapping
	    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO request) {
	        OrderResponseDTO responseDTO = orderService.placeOrder(request);
	        return ResponseEntity.ok(responseDTO);
	    }

	    @GetMapping("/{orderId}")
	    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable String orderId) {
	        OrderResponseDTO orderResponse = orderService.getOrderById(orderId);
	        return ResponseEntity.ok(orderResponse);
	    }

	    @GetMapping("/customer/{customerId}")
	    public ResponseEntity<?> getOrdersByCustomerId(@PathVariable String customerId) {
	        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
	    }
	    
	    @PatchMapping("/{orderId}/status")
	    public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatus status) {
	        orderService.updateOrderStatus(orderId, status);
	        return ResponseEntity.ok("Order status updated to " + status.name());
	    }
}
