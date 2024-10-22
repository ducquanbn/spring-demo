package com.example.springdemo.configuration;

import com.example.springdemo.common.Role;
import com.example.springdemo.entity.CustomerEntity;
import com.example.springdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    //Auto create admin user
    @Bean
    ApplicationRunner applicationRunner(CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.findByUsername("admin") == null) {
                Set<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());

                customerRepository.save(CustomerEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123456"))
                        .roles(roles)
                        .build());
            }
        };
    }
}
