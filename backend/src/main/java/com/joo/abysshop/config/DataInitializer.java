package com.joo.abysshop.config;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.factory.CartFactory;
import com.joo.abysshop.factory.UserFactory;
import com.joo.abysshop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        boolean exists = userRepository.findByUsername(adminUsername).isPresent();
        if (!exists) {
            String encryptedPassword = passwordEncoder.encode(adminPassword);
            User admin = UserFactory.ofAdmin(adminUsername, encryptedPassword);
            Cart cart = CartFactory.of(admin);
            admin.setUserCart(cart);
            userRepository.save(admin);
        }
    }
}

