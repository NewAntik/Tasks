package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Student;


public interface StudentService {
    
    @Transactional
    List<Student> saveAll(List<Student> students) throws SQLException;
    
    @Transactional(readOnly = true)    
    List<Student>  findStudentsRelatedCourseByName(String courseName) throws SQLException;
    
    @Transactional
    Student add(String login, String passwordHash ,String firstName, String lastName, Long groupId) throws SQLException;
    
    @Transactional
    void delete(Long id) throws SQLException;
    
    @Transactional(readOnly = true) 
    List<Student> findAll() throws SQLException;
    
    @Transactional(readOnly = true) 
    Student findByname(String name) throws SQLException;
    
    @Transactional
    Student save(Student student) throws SQLException;
}
