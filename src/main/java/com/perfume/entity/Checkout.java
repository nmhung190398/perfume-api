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
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToMany(mappedBy = "checkout",cascade = CascadeType.PERSIST)
    private List<CheckoutItem> checkoutItems;

    @Column
    private Integer paymentMethod;

    @Column
    private String address;

    @Column
    private String email;

    @Column String phone;

    @Column
    private Integer provinceId;

    @Column
    private Integer districtId;

    @Column
    private Integer wardId;

    public Checkout() {
    }

    public Checkout(User user, Coupon coupon, List<CheckoutItem> checkoutItems) {
        this.user = user;
        this.coupon = coupon;
        this.checkoutItems = checkoutItems;
    }
}
