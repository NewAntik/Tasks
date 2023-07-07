package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.dao.StudentRepository;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Student> findStudentsRelatedCourseByName(String courseName) throws SQLException {
        return studentRepository.findByCourseName(courseName);
    }
    
    @Override
    public void delete(Long id) throws SQLException {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAll() throws SQLException {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findByName(String name) throws SQLException {
        return studentRepository.findByFirstName(name);
    }

    @Override
    public Optional<Student> addNewStudent(String login, String password, String firstName, String lastName, Long groupId) {
        if (groupId == null || groupId == 0) {
            throw new IllegalArgumentException("Group Id should be mentiont!");
        }
        studentRepository.findByLogin(login).ifPresent(student -> {
            throw new IllegalArgumentException("Student with this login \"" + login + "\" already exists!");
        });
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NullPointerException("Group with this id \"" + groupId + "\" doesn't exist!" ));
        
        return Optional.of(studentRepository.save(new Student(login, passwordEncoder.encode(password), firstName, lastName, group)));
    } 
}
