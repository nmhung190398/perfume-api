package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
public class Target extends BaseEntity {

    public Target() {
        products = new HashSet<>();
    }

    public Target(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    private String name;

    @ManyToMany(mappedBy = "targets")
    private Set<Product> products;


}
