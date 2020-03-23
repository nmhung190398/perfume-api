package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
public class Rating extends BaseEntity {
    public int score;

    @Column(name = "user_id", insertable = false, updatable = false)
    public Long userId;

    @Column(name = "product_id", insertable = false, updatable = false)
    public Long productId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;
}
