package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@Entity
public class Property extends BaseEntity {
    public String name;

    @OneToMany(mappedBy = "property")
    public List<PropertyValue> propertyValues;
}
