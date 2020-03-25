package com.perfume.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class TargetDTO extends BaseDTO {

    private String name;

//    private Set<Product> products;


}
