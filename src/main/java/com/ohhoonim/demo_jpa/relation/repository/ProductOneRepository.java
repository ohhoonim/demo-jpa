package com.ohhoonim.demo_jpa.relation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohhoonim.demo_jpa.relation.ProductOneEntity;

public interface ProductOneRepository extends JpaRepository<ProductOneEntity, Long> {

    List<ProductOneEntity> findByCategories_product(ProductOneEntity product);
    
}
