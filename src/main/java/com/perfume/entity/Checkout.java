package com.perfume.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Checkout extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    public Coupon coupon;

    @OneToMany(mappedBy = "checkout")
    public List<CheckoutItem> checkoutItems;

    public Checkout() {
    }

    public Checkout(User user, Coupon coupon, List<CheckoutItem> checkoutItems) {
        this.user = user;
        this.coupon = coupon;
        this.checkoutItems = checkoutItems;
    }
}
