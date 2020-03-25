package com.perfume.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Product extends BaseEntity {
    public String name;

    @Column(unique=true)
    public String code;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    public String highlight;
    public String gender;
    public Date MFG;
    public Date EXP;
    @Lob
    public String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;
    
    @OneToMany(mappedBy = "product")
    public List<Version> versions;

    @OneToMany(mappedBy = "product")
    public List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    public Producer producer;

    @ManyToOne
    @JoinColumn(name = "amount_id")
    public Amount amount;

    @ManyToOne
    @JoinColumn(name = "fragrant_id")
    public Fragrant fragrant;

    @ManyToMany
    @JoinTable(name = "product_target")
    private List<Target> targets;

    public Product() {
    }
}
