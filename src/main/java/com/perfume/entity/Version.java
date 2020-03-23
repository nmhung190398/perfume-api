package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
public class Version extends BaseEntity {
    public String name;
    public long price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;
}
