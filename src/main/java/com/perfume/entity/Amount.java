package com.perfume.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Amount extends BaseEntity {
    public String name;
    public String description;

    @OneToMany(mappedBy = "amount")
    public List<Product> products;

    public Amount() {
    }

    public Amount(String name, String description, List<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }
}
