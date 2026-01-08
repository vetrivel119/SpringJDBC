package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository sr;

    public Student createStudent(Student student) throws IllegalAccessException {
        if(student.getAge() < 15 || student.getAge() > 30){
            throw new IllegalAccessException("Age must be beyween 15 and 30");
        }

        if(sr.existsByEmail(student.getEmail())){
            throw new IllegalAccessException("Email Already Exists" + student.getEmail());
        }

        String grade = student.getGrade().toUpperCase();
        if("ABCDF".contains(grade)){
            throw new IllegalAccessException("Grade must be A B C D F");
        }
        student.setGrade(grade);

        Integer id = sr.save(student);

        System.out.println("Student created with Id :" + student.getId());

        return student;
    }

    public List<Student> findAll(){
        return sr.findAll();
    }

    public Student findById(Integer id) throws IllegalAccessException {
        try{
            return sr.findById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new IllegalAccessException("Student not found with Id:" + id);
        }
    }

    public Student findByEmail(String email) throws IllegalAccessException {
        try{
            return sr.findByEmail(email);
        }
        catch (EmptyResultDataAccessException e){
            throw new IllegalAccessException("Student not found with Email:"+email);
        }
    }

    public List<Student> findByName(String name) throws IllegalAccessException {
        try{
            return sr.findByName(name);
        }
        catch (EmptyResultDataAccessException e){
            throw new IllegalAccessException("Student not found with Name:"+name);
        }
    }

    public int update(Integer id,Student student) throws IllegalAccessException {
        if(!sr.existsById(id)){
            throw new IllegalAccessException("Student not found with Id: "+id);
        }

        return sr.update(student);
    }

    public int delete(Integer id) throws IllegalAccessException {
        if(!sr.existsById(id)){
            throw new IllegalAccessException("Student not found with Id: "+id);
        }
        return sr.deletebyId(id);
    }

    public String getTotalRecords(){
        int total = sr.count();
        return "Total Records : " + total;
    }
}
