package com.ohhoonim.demo_jpa.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohhoonim.demo_jpa.relation.MtoMProductEntity;

public interface MtoMProductRepository extends JpaRepository<MtoMProductEntity, Long> {
    
}
