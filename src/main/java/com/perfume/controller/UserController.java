package com.perfume.controller;

import com.perfume.constant.RoleEnum;
import com.perfume.constant.StatusEnum;
import com.perfume.dto.*;
import com.perfume.dto.mapper.UserMapper;
import com.perfume.dto.mapper.UserMapper;
import com.perfume.entity.User;
import com.perfume.entity.Role;
import com.perfume.repository.UserRepository;
import com.perfume.repository.RoleRepository;
import com.perfume.repository.UserRepository;
import com.perfume.entity.User;
import com.perfume.sercurity.JwtToken;
import com.perfume.sercurity.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleRepository roleRepository;

//    @PostMapping("")
//    public ResponseEntity<ResponseMsg<User>> create(@RequestBody User body) {
//        body.setStatus(StatusEnum.ACTIVE.getValue());
//        body.setId(null);
//        userRepository.save(body);
//        return ResponseEntity.ok(new ResponseMsg<>(body,200,""));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMsg<User>> update(@RequestBody User body, @PathVariable Long id) {
        body.setStatus(null);
        body.setId(id);
        userRepository.update(body);
        return ResponseEntity.ok(new ResponseMsg<>(body,200,""));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMsg<Boolean>> delete(@PathVariable Long id) {
        userRepository.changeStatus(id, StatusEnum.DELETED.getValue());
        return ResponseEntity.ok(new ResponseMsg<>(true,200,""));
    }

    @GetMapping("")
    public ResponseEntity<ResponsePaging<UserDTO>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<User> pagedResult = userRepository.getAll(paging);
        List<UserDTO> users = new ArrayList<>();

        if (pagedResult.hasContent()) {
            users = pagedResult.getContent().stream().map(userMapper::toDTO).collect(Collectors.toList());
        }

        return ResponseEntity.ok(
                new ResponsePaging<>(users, new PagingDTO(pagedResult.getTotalElements(), page, limit, paging.getOffset()))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ValidationException("category does not exist");
        }
        return ResponseEntity.ok(userMapper.toDTO(user.get()));
    }

    // end crud

    @PostMapping("/filter")
    public ResponseEntity<List<UserDTO>> filter(@RequestBody User body) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        List<User> users = userRepository.find(body);

        return ResponseEntity.ok(
                users.stream().map(x -> userMapper.toDTO(x)).collect(Collectors.toList())
        );
    }

    @PostMapping("/filter/{page}/{limit}")
    public ResponseEntity<ResponsePaging<UserDTO>> filterPage(@RequestBody User body, @PathVariable int page, @PathVariable int limit) {
        body.setStatus(StatusEnum.ACTIVE.getValue());
        Pageable paging = PageRequest.of(page - 1, limit);
        Page<User> pagedResult = userRepository.findPage(body, paging);
        List<UserDTO> users = new ArrayList<>();
        if (pagedResult.hasContent()) {
            users = pagedResult.getContent().stream().map(userMapper::toDTO).collect(Collectors.toList());
        }
        PagingDTO pagingDTO = new PagingDTO(pagedResult.getTotalElements(), page, limit, paging.getOffset());
        return ResponseEntity.ok(new ResponsePaging<>(users, pagingDTO));
    }



    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) {
        String username = user.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new ValidationException("Username already existed");
        }







        String password = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        Role role = roleRepository.findByName(RoleEnum.ROLE_MEMBER.toString());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(encodedPassword);
        user.setStatus(StatusEnum.ACTIVE.getValue());
        userRepository.save(user);

        user.setPassword("");
        return ResponseEntity.ok(user);
    }
}
