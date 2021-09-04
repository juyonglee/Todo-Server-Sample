package com.gmail.juyonglee0208;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoServerApplication {
    public static void main(String[] args) {
        System.out.println("Hello Todo Server");
        SpringApplication.run(TodoServerApplication.class, args);
    }
}
