package org.example.categoryservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced // <--- To sprawia, że RestTemplate pyta Eurekę o adresy!
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}