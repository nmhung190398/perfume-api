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
}
