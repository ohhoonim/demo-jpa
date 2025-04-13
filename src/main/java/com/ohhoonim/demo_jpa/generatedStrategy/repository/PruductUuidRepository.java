package com.ohhoonim.demo_jpa.generatedStrategy.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohhoonim.demo_jpa.generatedStrategy.ProductUuidEntity;

public interface PruductUuidRepository extends JpaRepository<ProductUuidEntity, UUID> {
    
}
