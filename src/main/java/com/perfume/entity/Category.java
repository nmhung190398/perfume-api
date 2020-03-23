package com.perfume.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


public class Category {
    @Id
    @GeneratedValue
    public String id;

    public String name;
    public String code;
    public String description;
    public int status;
    public Date createdAt;
    public Date updatedAt;
}
