package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
public class Category extends BaseEntity {
    public String name;

    @Column(unique=true)
    public String code;

    @Lob
    public String description;

    @OneToMany(mappedBy = "category")
    public List<Product> products;

    public Category() {
    }

    public Category(String name, String code, String description, List<Product> products) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.products = products;
    }
}
