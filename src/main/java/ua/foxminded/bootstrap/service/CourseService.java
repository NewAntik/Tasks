package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Course;

public interface CourseService {
    
    @Transactional
    List<Course> saveAll(List<Course> courses) throws SQLException;
    
    @Transactional(readOnly = true)
    public List<Course> findAll() throws SQLException;
    
    
}
