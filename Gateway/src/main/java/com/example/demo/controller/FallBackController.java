package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class FallBackController {
	 @GetMapping("/fallback/products")
	    public ResponseEntity<String> productFallback() {
		 
	        return ResponseEntity.ok("Product Service is down. Please try later!");
	    }
}
