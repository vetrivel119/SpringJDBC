package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> StudentMapper = (rs,rowNum)->{
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setAge(rs.getInt("age"));
        student.setGrade(rs.getString("grade"));
        student.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        student.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return student;
    };

    public Integer save(Student student){
        String sql = "INSERT INTO students (name, email, age, grade) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getGrade());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public List<Student> findAll(){
        String sql = "SELECT * FROM students ORDER BY name";
        return jdbcTemplate.query(sql, StudentMapper);
    }

    public Student findById(Integer id){
        String sql = "SELECT * FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, StudentMapper, id);
    }

    public Student findByEmail(String email){
        String sql = "SELECT * FROM students WHERE email = ?";
        return jdbcTemplate.queryForObject(sql,StudentMapper,email);
    }

    public List<Student> findByName(String name){
        String sql = "SELECT * FROM students WHERE name LIKE ? ORDER BY name";
        return jdbcTemplate.query(sql,StudentMapper,"%"+name+"%");
    }

    public int update(Student student){
        String sql = "UPDATE students SET name = ?,email=?,age=?,grade=? WHERE id = ?";
        return jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getAge(), student.getGrade(), student.getId());
    }

    public int deletebyId(Integer id){
        String sql ="DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public int count(){
        String sql = "SELECT COUNT(*) FROM students";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    public boolean existsById(Integer id){
        String sql ="SELECT count(*) FROM students WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,id);
        return count!= null && count > 0;
    }

    public boolean existsByEmail(String email){
        String sql ="SELECT count(*) FROM students WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,email);
        return count!= null && count > 0;
    }
}
