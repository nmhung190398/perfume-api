package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
public class Product extends BaseEntity {
    public String name;

    @Column(unique=true)
    public String code;
    public String highlight;
    public String gender;
    public Date MFG;
    public Date EXP;
    @Lob
    public String description;

    @ManyToOne
    public Category category;
    
    @OneToMany(mappedBy = "product")
    public List<Version> versions;

    @OneToMany(mappedBy = "product")
    public List<CartItem> cartItems;
}
