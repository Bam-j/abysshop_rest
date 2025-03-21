package com.joo.abysshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.joo.abysshop.repository")
public class AbysshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbysshopApplication.class, args);
    }

}
