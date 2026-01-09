package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService ss;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = ss.findAll();
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try{
            Student s = ss.findById(id);
            return ResponseEntity.ok().body(s);
        }
        catch(IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error",e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam String name) throws IllegalAccessException {
        try {
            List<Student> students = ss.findByName(name);
            return ResponseEntity.ok(students);
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error",e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student s){
        try{
            Student created = ss.createStudent(s);
            return ResponseEntity.ok().body(created);
        }
        catch(IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error",e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable Integer id,
            @RequestBody Student s
    ){
        try{
            int student = ss.update(id,s);
            return ResponseEntity.ok().body(student);
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error",e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        try{
            ss.delete(id);
            return ResponseEntity.ok(Map.of("Message","Student deleted sucessfully"));
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error",e.getMessage()));
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<String> getStatistics() {
        String stats = ss.getTotalRecords();
        return ResponseEntity.ok(stats);
    }

}
