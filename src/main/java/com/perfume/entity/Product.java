package com.perfume.entity;

import com.nmhung.anotation.QueryField;
import com.nmhung.anotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@TableName
public class Product extends BaseEntity {
    public String name;

    @Column(unique = true)
    public String code;
    public String highlight;
    public String gender;
    public Date MFG;
    public Date EXP;
    @QueryField
    public String image;
    @Lob
    public String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    @QueryField(name = "category.id")
    @Column(name = "category_id", updatable = false, insertable = false)
    public Long categoryId;

    @OneToMany(mappedBy = "product")
    public List<Version> versions;

    @OneToMany(mappedBy = "product")
    public List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    public Producer producer;

    @QueryField(name = "producer.id")
    @Column(name = "producer_id", updatable = false, insertable = false)
    public Long producerId;

    @ManyToOne
    @JoinColumn(name = "amount_id")
    public Amount amount;

    @QueryField(name = "amount.id")
    @Column(name = "amount_id", updatable = false, insertable = false)
    public Long amountId;

    @ManyToOne
    @JoinColumn(name = "fragrant_id")
    public Fragrant fragrant;

    @QueryField(name = "fragrant.id")
    @Column(name = "fragrant_id", updatable = false, insertable = false)
    public Long fragrantId;

    @ManyToMany
    @JoinTable(name = "product_target")
    private List<Target> targets;

    public Product() {
    }

    public Product(String name, String code, String highlight, String gender, Date MFG, Date EXP, String image, String description, Category category, Long categoryId, List<Version> versions, List<CartItem> cartItems, Producer producer, Long producerId, Amount amount, Long amountId, Fragrant fragrant, Long fragrantId, List<Target> targets) {
        this.name = name;
        this.code = code;
        this.highlight = highlight;
        this.gender = gender;
        this.MFG = MFG;
        this.EXP = EXP;
        this.image = image;
        this.description = description;
        this.category = category;
        this.categoryId = categoryId;
        this.versions = versions;
        this.cartItems = cartItems;
        this.producer = producer;
        this.producerId = producerId;
        this.amount = amount;
        this.amountId = amountId;
        this.fragrant = fragrant;
        this.fragrantId = fragrantId;
        this.targets = targets;
    }
}
