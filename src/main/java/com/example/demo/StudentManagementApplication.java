package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);

        System.out.println("════════════════════════════════════════════");
        System.out.println("Student Management API Started!");
        System.out.println("Base URL: http://localhost:8081/api/students");
        System.out.println("════════════════════════════════════════════");
	}

}
