package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.ProductClient;
import com.example.demo.dto.OrderItemRequestDTO;
import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.dto.OrderStatus;
import com.example.demo.dto.ProductResponseDTO;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Orders;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
		
	
	  @Autowired private OrderRepository orderRepository;
	  @Autowired  private OrderItemRepository orderItemRepository;
	  @Autowired  private ProductClient productClient;
	  
	  public OrderResponseDTO placeOrder(OrderRequestDTO requestDTO) {
		  
		   String orderId = generateOrderId();
	       double totalAmount = 0.0;
		  
	       List<OrderItem> orderItems = new ArrayList<>();
	       
	       for(OrderItemRequestDTO itemRequest : requestDTO.getItems()) {
	    	   
	            ProductResponseDTO product = productClient.getProductById(itemRequest.getProductId());
	            
	            if(product.getStockQuantity() < itemRequest.getQuantity()) {
	                throw new RuntimeException("Insufficient stock for product: " + product.getName());
	            }
	            productClient.updateStock(itemRequest.getProductId(), -itemRequest.getQuantity());

	            double itemTotal = itemRequest.getQuantity() * product.getPrice();
	            totalAmount += itemTotal;

	            OrderItem orderItem = new OrderItem(generateOrderItemId(), orderId, itemRequest.getProductId(), itemRequest.getQuantity(), product.getPrice());
	            orderItems.add(orderItem);
	        }
	       
	       Orders order = new Orders(orderId, requestDTO.getCustomerId(),LocalDateTime.now(), totalAmount, OrderStatus.PENDING);
	       
	       orderRepository.save(order);
	       orderItemRepository.saveAll(orderItems);

	        return new OrderResponseDTO(order.getOrderId(), order.getCustomerId(), order.getOrderDate(), order.getTotalAmount(), order.getStatus(), orderItems);
	  }
	  
	  
	  public OrderResponseDTO getOrderById(String orderId) {
	        Orders order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

	        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
	        return new OrderResponseDTO(order.getOrderId(), order.getCustomerId(),
	                order.getOrderDate(), order.getTotalAmount(), order.getStatus(), items);
	    }

	    public List<OrderResponseDTO> getOrdersByCustomerId(String customerId) {
	    	
	        List<Orders> orders = orderRepository.findByCustomerId(customerId);
	        List<OrderResponseDTO> responseList = new ArrayList<>();

	        for (Orders order : orders) {
	            List<OrderItem> items = orderItemRepository.findByOrderId(order.getOrderId());
	            responseList.add(new OrderResponseDTO(order.getOrderId(), order.getCustomerId(),
	                    order.getOrderDate(), order.getTotalAmount(), order.getStatus(), items));
	        }
	        return responseList;
	    }

	    public void updateOrderStatus(String orderId, OrderStatus orderStatus) {
	        Orders order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

	        order.setStatus(orderStatus);
	        orderRepository.save(order);
	    }

	  
	  
	    private String generateOrderId() {
	        return "ord-" + UUID.randomUUID().toString().substring(0, 8);
	    }
	    private String generateOrderItemId() {
	        return "item-" + UUID.randomUUID().toString().substring(0, 8);
	    }
	   

}
