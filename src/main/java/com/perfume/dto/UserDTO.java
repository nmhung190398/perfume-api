package com.perfume.dto;


//import com.perfume.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public String address;
    public String phone;
    public String password;
    public String confirmPassword;
    public List<RoleDTO> roles;
    public String oldPassworld;
}
