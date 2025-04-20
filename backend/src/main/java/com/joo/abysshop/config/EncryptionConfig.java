package com.joo.abysshop.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "custom.encrypt")
public class EncryptionConfig {
    private String secretKey;
    private String initVector;
}
