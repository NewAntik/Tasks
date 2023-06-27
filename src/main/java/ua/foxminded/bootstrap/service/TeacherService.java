package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Teacher;

public interface TeacherService {

    @Transactional
    List<Teacher> saveAll(List<Teacher> teachers) throws SQLException;
    
    @Transactional(readOnly = true)    
    List<Teacher> findTeachersBySpecialization(String specialization) throws SQLException;
    
    @Transactional
    Teacher add(String login, String passwordHash, String firstName, String lastName) throws SQLException;
    
    @Transactional
    void delete(Long id) throws SQLException;
    
    @Transactional(readOnly = true) 
    List<Teacher> findAll() throws SQLException;

    @Transactional(readOnly = true)
    Teacher findByName(String username);
    
    @Transactional
    Teacher save(Teacher teacher) throws SQLException;
    
 }
