package com.example.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ProductResponseDTO;

@Component
public class ProductClient {
	
	@Autowired private RestTemplate rt;
	
	public ProductResponseDTO getProductById(String productId) {
        String url = "http://localhost:8080/prod/" + productId;
        return rt.getForObject(url, ProductResponseDTO.class);
    }

    public void updateStock(String productId, int quantity) {
        String url = "http://localhost:8080/prod/"+ productId+ "/stock?stockQuantity="+ quantity;
        rt.patchForObject(url, null, Void.class);
    }
}
