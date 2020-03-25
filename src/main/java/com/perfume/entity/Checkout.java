package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
public class Checkout extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    public Coupon coupon;

    @OneToMany(mappedBy = "checkout")
    public List<CheckoutItem> checkoutItems;
}
