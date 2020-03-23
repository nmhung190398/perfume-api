package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@Entity
public class Amount extends BaseEntity {
    public String name;
    public String description;
}
