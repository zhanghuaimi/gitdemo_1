package com.example.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "demo.jw")
public class JwProperties {
    String userSecretKey;
    long userTtl;
    String userToKenName;
}
