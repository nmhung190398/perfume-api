package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
public class PropertyValue extends BaseEntity {
    public String name;

    @ManyToOne
    @JoinColumn(name = "property_id")
    public Property property;
}
