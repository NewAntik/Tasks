package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Student;


public interface StudentService {
    
    @Transactional(readOnly = true)    
    List<Student>  findStudentsRelatedCourseByName(String courseName) throws SQLException;
    
    @Transactional
    void delete(Long id) throws SQLException;
    
    @Transactional(readOnly = true) 
    List<Student> findAll() throws SQLException;
    
    @Transactional(readOnly = true) 
    Optional<Student> findByName(String name) throws SQLException;

    @Transactional
    Optional<Student> addNewStudent(String login, String password, String firstName, String lastName, Long groupId);

    @Transactional(readOnly = true)
    Student findById(Long studentId);
}
