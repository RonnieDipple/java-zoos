package com.example.javazoosassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JavaZoosAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaZoosAssignmentApplication.class, args);
    }

}
