package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@Entity
public class Producer extends BaseEntity {
    public String name;
    @Lob
    public String description;

    @OneToMany(mappedBy = "producer")
    public List<Product> products;
}
