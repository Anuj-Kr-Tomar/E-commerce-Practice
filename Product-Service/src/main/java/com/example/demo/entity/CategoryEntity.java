package com.example.demo.entity;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
		
	    @Id
	    private String categoryId;
	    private String name;
	    private String description;
	    
	    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	    private List<ProductEntity> products;
	    
	    
	    @PrePersist
	    public void generateId() {
	        if (this.categoryId == null) {
	            this.categoryId = "cat-"+String.format("%05d", com.example.demo.config.IdGenerator.getNextCategoryId());
	        }
	    }
}
