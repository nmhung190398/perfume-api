package com.perfume.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Builder
@Entity
@Data
public class User extends BaseEntity {
    @Column(unique = true)
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public String address;
    public String phone;
    public String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    public List<Role> roles;

    @OneToMany(mappedBy = "user")
    public List<CartItem> cartItems;

    public User() {
    }

    public User(String username, String firstname, String lastname, String email, String address, String phone, String password, List<Role> roles, List<CartItem> cartItems) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.roles = roles;
        this.cartItems = cartItems;
    }
}
