package com.perfume.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class VersionDTO extends BaseDTO {
    public String name;
    public long price;

//    public Product product;
}
