package com.joo.abysshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan(basePackages = "com.joo.abysshop.mapper.mybatis")
public class AbysshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbysshopApplication.class, args);
	}

}
