package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.dao.StudentRepository;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRep;
    private final GroupRepository groupRep;

    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRep = studentRepository;
        this.groupRep = groupRepository;
    }

    @Override
    public List<Student> findStudentsRelatedCourseByName(String courseName) throws SQLException {
        return studentRep.findByCourseName(courseName);
    }

    @Override
    public Student add(String login, String passwordHash ,String firstName, String lastName, Long groupId) throws SQLException {
        if (groupRep.findById(groupId).isPresent()) {
            
            return studentRep.save(new Student(login, passwordHash, firstName, lastName, groupRep.findById(groupId).get()));
          } else {
              throw new IllegalArgumentException("Group with this id" + groupId + "doesn't exist!");
          }
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
    public List<Student> saveAll(List<Student> students) throws SQLException {
        return studentRep.saveAll(students);
    }
}
