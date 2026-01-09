# 🎓 Student Management System

A RESTful API built with Spring Boot and JDBC for managing student records.

## 🚀 Technologies Used

- Java 17
- Spring Boot 3.2.0
- Spring JDBC
- MySQL 8.0
- HikariCP (Connection Pooling)
- Maven

## 📋 Features

- ✅ Complete CRUD operations for students
- ✅ Search students by name
- ✅ Input validation (age, grade, email)
- ✅ Transaction management
- ✅ Connection pooling with HikariCP
- ✅ RESTful API design

## 🛠️ Setup Instructions

### Prerequisites
- Java 17 or higher
- MySQL 8.0
- Maven 3.6+

### Database Setup
```sql
CREATE DATABASE student_management;
USE student_management;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INT NOT NULL,
    grade VARCHAR(2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### Configuration
Update `application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run Application
```bash
mvn spring-boot:run
```

Application will start on `http://localhost:8080`

## 📡 API Endpoints

### Get All Students
```
GET /api/students
```

### Get Student by ID
```
GET /api/students/{id}
```

### Search Students
```
GET /api/students/search?keyword=john
```

### Create Student
```
POST /api/students
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@example.com",
    "age": 20,
    "grade": "A"
}
```

### Update Student
```
PUT /api/students/{id}
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john.updated@example.com",
    "age": 21,
    "grade": "A"
}
```

### Delete Student
```
DELETE /api/students/{id}
```

### Get Statistics
```
GET /api/students/stats
```

## 📁 Project Structure
```
src/
├── main/
│   ├── java/com/myapp/
│   │   ├── StudentManagementApplication.java
│   │   ├── controller/
│   │   │   └── StudentController.java
│   │   ├── service/
│   │   │   └── StudentService.java
│   │   ├── repository/
│   │   │   └── StudentRepository.java
│   │   └── model/
│   │       └── Student.java
│   └── resources/
│       └── application.properties
└── schema.sql
```

## 🎯 What I Learned

- ✅ Raw JDBC operations with PreparedStatement
- ✅ Connection pooling with HikariCP
- ✅ Spring Boot auto-configuration
- ✅ JdbcTemplate for simplified database access
- ✅ Layered architecture (Controller → Service → Repository)
- ✅ RESTful API design principles
- ✅ Transaction management with @Transactional
- ✅ Input validation and error handling

Built with ❤️ while learning JDBC and Spring Boot
