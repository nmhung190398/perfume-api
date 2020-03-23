package com.perfume.entity;

import javax.persistence.Lob;

public class Producer extends BaseEntity {
    public String name;
    @Lob
    public String description;
}
