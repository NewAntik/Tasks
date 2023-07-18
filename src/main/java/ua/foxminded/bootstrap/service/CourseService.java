package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Course;

public interface CourseService {
    
    @Transactional
    List<Course> saveAll(List<Course> courses) throws SQLException;
    
    @Transactional(readOnly = true)
    List<Course> findAll() throws SQLException;
    
    @Transactional
    Optional<Course> addNewCourse(String name, String description) throws SQLException;
    
    @Transactional
    void deleteCourseById(Long id) throws SQLException;
    
    @Transactional
    void updateCourse(Long id, String newName, String newDescription) throws SQLException;
    
    @Transactional
    void assignTeacherToCourse(Long teacherId, Long courseId);
    
    @Transactional
    void assignGroupToCourse(Long groupId, Long courseId);
    
    @Transactional
    void reassignTeacherToCourse(Long teacherId, Long courseId);
    
    @Transactional
    void reassignGroupToCourse(Long groupId, Long courseId);
}
