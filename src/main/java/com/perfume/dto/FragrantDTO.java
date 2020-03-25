package com.perfume.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class FragrantDTO extends BaseDTO {
    public String name;
    public String description;

//    public List<Product> products;
}
