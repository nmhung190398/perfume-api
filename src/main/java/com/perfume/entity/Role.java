package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Builder
@Entity
public class Role {
    @Id
    public long id;

    public String name;

    @ManyToMany(mappedBy = "roles")
    public List<User> users;
}
