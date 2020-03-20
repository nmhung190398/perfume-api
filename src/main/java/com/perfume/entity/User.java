package com.perfume.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Builder
@Entity
public class User {
    @Id
    public Long id;

    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public String address;
    public String phone;
    public String password;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public List<Role> roles;
}
