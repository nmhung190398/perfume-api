package com.perfume.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CheckoutItem extends BaseEntity {

    @Column(name = "user_id", insertable = false, updatable = false)
    public Long userId;

    @Column(name = "version_product_id", insertable = false, updatable = false)
    public Long versionProductId;

    public int quantity;

    @ManyToOne
    @JoinColumn(name = "version_product_id")
    public Version version;
    @ManyToOne
    @JoinColumn(name = "checkout_id")
    public Checkout checkout;

    public CheckoutItem() {
    }

    public CheckoutItem(Long userId, Long versionProductId, int quantity, Version version, Checkout checkout) {
        this.userId = userId;
        this.versionProductId = versionProductId;
        this.quantity = quantity;
        this.version = version;
        this.checkout = checkout;
    }
}
