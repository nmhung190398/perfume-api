package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
public class Target extends BaseEntity {
    public String name;

    @ManyToMany(mappedBy = "targets")
    public List<Product> products;
}
