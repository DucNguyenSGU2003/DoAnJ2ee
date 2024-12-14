package com.UniCharity.UniCharity.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary configKey() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "drstycnzm");
        config.put("api_key", "715491167861313");
        config.put("api_secret", "87ieNTqo-3F26Ozg685P2vxgBc0");
        return new Cloudinary(config);
    }
}
