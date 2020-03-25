package com.perfume.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Coupon extends BaseEntity {

    @Column(unique=true)
    public String code;

    public Date MFG;

    public int total;

    @OneToMany(mappedBy = "coupon")
    List<Checkout> checkouts;
}
