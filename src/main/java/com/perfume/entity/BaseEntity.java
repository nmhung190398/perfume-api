package com.perfume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nmhung.anotation.QueryField;
import com.nmhung.anotation.Supperclass;
import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@Supperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @QueryField(id = true)
    private Long id;

    @QueryField
    @JsonIgnore
    private Integer status;
    @QueryField
    private Date createdAt;
    @QueryField
    private Date updatedAt;

    public BaseEntity() {
    }
}
