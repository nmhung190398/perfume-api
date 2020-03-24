package com.perfume;

import com.perfume.constant.RoleEnum;
import com.perfume.constant.StatusEnum;
import com.perfume.entity.Role;
import com.perfume.entity.User;
import com.perfume.repository.RoleRepository;
import com.perfume.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBSeed {
//    private final String MEMBER = "Test";
//    private final String ADMIN  = "Admin";
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @EventListener
//    public void seed(ContextRefreshedEvent event) {
//        seedRolesTable();
//        seedUsersTable();
//    }
//
//    private void seedRolesTable() {
//
//    }
//
//    private void seedUsersTable() {
//        User testUser = userRepository.findByUsername(MEMBER);
//        User admin = userRepository.findByUsername(ADMIN);
//        if (testUser == null) {
//            User user = new User();
//            Role role = roleRepository.findByName(RoleEnum.ROLE_MEMBER.toString());
//            role.builder().users(Collections.singletonList(user)).build();
//            List<Role> roles = new ArrayList<>();
//            roles.add(role);
//            user.builder()
//                    .username(MEMBER)
//                    .password(new BCryptPasswordEncoder().encode("123456"))
//                    .roles(roles)
//                    .build();
//            userRepository.save(user);
//        }
//        if (admin == null) {
//            User user = new User();
//            List<Role> roles = roleRepository.findAll();
//            for (Role role:
//                 roles) {
//                role.setUsers(Collections.singletonList(user));
//            }
//        }
//    }
}
