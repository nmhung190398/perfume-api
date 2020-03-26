package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
public class CheckoutItem extends BaseEntity {

    @Column(name = "user_id", insertable = false, updatable = false)
    public Long userId;

    @Column(name = "product_id", insertable = false, updatable = false)
    public Long productId;

    public int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "checkout_id")
    public Checkout checkout;
}
