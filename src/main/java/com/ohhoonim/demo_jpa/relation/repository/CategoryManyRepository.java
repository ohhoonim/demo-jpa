package com.ohhoonim.demo_jpa.relation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohhoonim.demo_jpa.relation.CategoryManyEntity;
import com.ohhoonim.demo_jpa.relation.ProductOneEntity;

public interface CategoryManyRepository extends JpaRepository<CategoryManyEntity, Long> {

    List<CategoryManyEntity> findByProduct(ProductOneEntity product);
    
}
