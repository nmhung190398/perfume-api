package com.perfume.entity;

import com.perfume.dto.BaseDTO;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
public class CartItem extends BaseEntity {

    @Column(name = "user_id", insertable = false, updatable = false)
    public Long userId;

    @Column(name = "product_id", insertable = false, updatable = false)
    public Long productId;

    public int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;
}
