package com.ohhoonim.demo_jpa.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohhoonim.demo_jpa.relation.MtoMCategoryEntity;

public interface MtoMCategoryRepository extends JpaRepository<MtoMCategoryEntity, Long> {
    
}
