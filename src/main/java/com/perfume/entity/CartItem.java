package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

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
