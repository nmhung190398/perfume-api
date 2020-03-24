package com.perfume.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique=true)
    public String code;

    public Date MFG;

    public int total;

    @OneToMany(mappedBy = "coupon")
    List<Checkout> checkouts;
}
