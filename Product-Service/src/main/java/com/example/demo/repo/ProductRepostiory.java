package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProductEntity;

public interface ProductRepostiory extends JpaRepository<ProductEntity,String> {

}
