package com.perfume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Data
@Builder
@Entity
public class User extends BaseEntity {
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public String address;
    public String phone;
    public String password;

    @ManyToMany
    @JoinTable(name = "user_role")
    @JsonIgnore
    public List<Role> roles;

    @OneToMany(mappedBy = "user")
    public List<CartItem> cartItems;
}
