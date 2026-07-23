package com.example.issuetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cross-Origin Resource Sharing (CORS) configuration.
 *
 * <p>The WPF desktop client calls this API from a different origin than the server. While a
 * native HttpClient is not strictly bound by browser CORS rules, enabling CORS keeps the API
 * usable from browser-based tooling (Swagger UI, a future web front-end) and documents the
 * intent explicitly.
 *
 * <p>Design note: the allowed origins below are permissive for local development. In production
 * you would narrow {@code allowedOrigins} to the exact host(s) that may call the API.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                // Localhost origins commonly used during development.
                .allowedOrigins("http://localhost", "http://localhost:3000", "http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
