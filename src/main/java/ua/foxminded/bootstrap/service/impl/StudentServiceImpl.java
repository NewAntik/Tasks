package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.StudentRepository;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRep;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRep = studentRepository;
    }

    @Override
    public List<Student> findStudentsRelatedCourseByName(String courseName) throws SQLException {
        return studentRep.findByCourseName(courseName);
    }
    
    @Override
    public void delete(Long id) throws SQLException {
        studentRep.deleteById(id);
    }

    @Override
    public List<Student> findAll() throws SQLException {
        return studentRep.findAll();
    }

    @Override
    public Student findByName(String name) throws SQLException {
        return studentRep.findByFirstName(name) ;
    }
}
