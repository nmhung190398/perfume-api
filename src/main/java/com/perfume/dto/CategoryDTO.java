package com.perfume.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class CategoryDTO extends BaseDTO {
    public String name;
    public String code;
    public String description;
}
