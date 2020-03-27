package com.perfume.entity;

import com.perfume.dto.BaseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CartItem extends BaseEntity {

    @Column(name = "user_id", insertable = false, updatable = false)
    public Long userId;

    @Column(name = "version_product_id", insertable = false, updatable = false)
    public Long versionProductId;

    public int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "version_product_id")
    public Version version;

    public CartItem() {
    }

    public CartItem(Long userId, Long versionProductId, int quantity, User user, Version version) {
        this.userId = userId;
        this.versionProductId = versionProductId;
        this.quantity = quantity;
        this.user = user;
        this.version = version;
    }
}
