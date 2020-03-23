package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@Entity
public class Fragrant extends BaseEntity {
    public String name;
    public String description;
}
