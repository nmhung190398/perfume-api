package com.perfume.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

    private final String token;
    private String username;
    private String role;

    public JwtResponse(String token) {

        this.token = token;

    }

    public JwtResponse(String token, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.role = authorities.stream().findFirst().get().toString(); // 1 role per person for now
    }

    public String getToken() {

        return this.token;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
