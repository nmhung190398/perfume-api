package com.perfume.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
public class Role {
    @Id
    public long id;

    public String name;

    @ManyToMany(mappedBy = "roles")
    public List<User> users;

    public Role() {
    }
}
