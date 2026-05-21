package com.example.authdemo.config;

import com.example.authdemo.entity.AppUser;
import com.example.authdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of("ADMIN", "USER"));
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                AppUser user = new AppUser();
                user.setUsername("user");
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRoles(Set.of("USER"));
                userRepository.save(user);
            }
        };
    }
}
