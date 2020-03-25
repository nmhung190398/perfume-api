package com.perfume.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class ProducerDTO extends BaseDTO {
    public String name;
    @Lob
    public String description;

//    public List<Product> products;
}
