package com.perfume.controller;

import com.perfume.constant.RoleEnum;
import com.perfume.entity.JwtRequest;
import com.perfume.entity.JwtResponse;
import com.perfume.entity.Role;
import com.perfume.repository.UserRepository;
import com.perfume.entity.User;
import com.perfume.sercurity.JwtToken;
import com.perfume.sercurity.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtTokenUtil;

    @PostMapping("/add")
    public ResponseEntity<User> create(@RequestBody User body) throws NoSuchAlgorithmException {
        String username = body.getUsername();
        if (userRepository.existsByUsername(username))
        {
            throw new ValidationException("Username already existed");
        }

        String password = body.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        List<Role> roles = Arrays.asList(
                Role.builder().name(RoleEnum.MEMBER.toString()).build());

        User user;
        user = body.builder().roles(roles).password(encodedPassword).build();

        userRepository.save(user);
        user.setPassword("");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(), authenticationRequest.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return  ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
