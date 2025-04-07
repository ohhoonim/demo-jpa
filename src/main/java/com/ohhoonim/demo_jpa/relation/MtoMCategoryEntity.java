package com.ohhoonim.demo_jpa.relation;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "category_many2many")
@Entity
public class MtoMCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "product_category_many2many",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<MtoMProductEntity> products;  
}

/*
create table category_many2many (
    id bigint generated by default as identity,
    name varchar(255),
    primary key (id)
)

create table product_category_many2many (
    category_id bigint not null,
    product_id bigint not null
)

alter table if exists product_category_many2many 
    add constraint FKknsirv1skbgcor7s9ogdfyjat 
    foreign key (category_id) 
    references product_many2many

alter table if exists product_category_many2many 
    add constraint FK454chylfdrx3xqy0e2m5odllk 
    foreign key (product_id) 
    references category_many2many

 */