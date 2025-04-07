package com.ohhoonim.demo_jpa.generatedStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "product_uuid")
@Entity
public class ProductUuidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;  //<- 여기가 UUID로 바뀜

    @Comment("상품명")
    private String name;

    private BigDecimal price;

    private LocalDateTime createdAt;
}

/*
 create table product_uuid (
        price numeric(38,2),
        created_at timestamp(6),
        id uuid not null,
        name varchar(255),
        primary key (id)
    ) 
 */