package com.perfume;

import com.perfume.constant.RoleEnum;
import com.perfume.entity.Role;
import com.perfume.entity.User;
import com.perfume.repository.RoleRepository;
import com.perfume.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@Configuration
public class Application implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // Khi chương trình chạy
        // Insert vào csdl một user.
        User user = User.builder().username("admin").password(new BCryptPasswordEncoder().encode("123456")).build();
        if (userRepository.findByUsername(user.getUsername()) == null) {

//            user.setRoles(roleRepository.findAll());
//            user = userRepository.save(user);
            System.out.println(user);
        }

        List<Role> roles = Arrays.asList(
                Role.builder().name(RoleEnum.ADMIN.toString()).users(Arrays.asList(user)).build(),
                Role.builder().name(RoleEnum.EMPLOYEE.toString()).users(Arrays.asList(user)).build(),
                Role.builder().name(RoleEnum.MEMBER.toString()).users(Arrays.asList(user)).build()
        );
        for (int i = 0; i < roles.size(); ++i) {
            if (roleRepository.findByName(roles.get(i).getName()) == null) {
                roleRepository.save(roles.get(i));
            }

        }
        user.setRoles(roleRepository.findAll());
        user = userRepository.save(user);

    }
}
