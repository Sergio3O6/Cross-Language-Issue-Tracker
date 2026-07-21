package com.example.issuetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 *
 * <p>{@code @SpringBootApplication} bundles three annotations:
 * <ul>
 *   <li>{@code @Configuration}      - this class can declare beans,</li>
 *   <li>{@code @EnableAutoConfiguration} - Spring Boot wires up Tomcat, Jackson, JPA, etc.,</li>
 *   <li>{@code @ComponentScan}      - discovers controllers/services/repositories in this
 *       package and below ({@code com.example.issuetracker.*}).</li>
 * </ul>
 */
@SpringBootApplication
public class IssueTrackerApplication {

    public static void main(String[] args) {
        // Boots the embedded web server and the whole application context.
        SpringApplication.run(IssueTrackerApplication.class, args);
    }
}
