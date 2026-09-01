package com.purchase.billingsoftware.config;

import com.purchase.billingsoftware.entity.UserEntity;
import com.purchase.billingsoftware.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String email = "manu@gmail.com";

        if (userRepository.findByEmail(email).isEmpty()) {

            UserEntity user = new UserEntity();

            user.setName("Manasa");
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ROLE_ADMIN");

            userRepository.save(user);

            System.out.println("Admin user created successfully.");
        }
    }
}
