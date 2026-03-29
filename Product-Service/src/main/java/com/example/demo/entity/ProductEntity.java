package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.config.IdGenerator;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ProductEntity {
	
	@Id
	
	private String productId;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private Boolean inStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    
    @PrePersist
    @PreUpdate
    public void updateStockStatus() {
        this.inStock = this.stockQuantity != null && this.stockQuantity > 0;
        if(createdAt == null) createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        
        
        if(this.productId == null) {
            this.productId = "prod-"+String.format("%05d", IdGenerator.getNextProductId());
        }

        
    }


}
