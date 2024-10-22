package com.vendor_managemnt.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimiterConfig {

    @Bean
    public Bucket bucket() {
        Bandwidth limit = Bandwidth.classic(2, Refill.greedy(2, Duration.ofSeconds(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}
