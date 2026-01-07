package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private String grade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Student(){};

    public Student(Integer id, String name, String email,Integer age,String grade){
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString(){
        return "Student{id=" + id + ", name='" + name + "', email='" + email +
                "', age=" + age + ", grade='" + grade + "'}";
    }
}
