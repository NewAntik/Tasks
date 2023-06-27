package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.dao.StudentRepository;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    
    private final PasswordEncoder passwordEnc;
    private final StudentRepository studentRep;
    private final GroupRepository groupRep;

    public StudentServiceImpl(PasswordEncoder passwordEncoder, StudentRepository studentRepository, GroupRepository groupRepository) {
        this.passwordEnc = passwordEncoder;
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
        List<Student> encodedStudents = students.stream()
                .map(student -> new Student(student.getLogin(), passwordEnc.encode(student.getPasswordHash()),
                        student.getFirstName(), student.getLastName(), student.getGroup()))
                .collect(Collectors.toList());
        
        return studentRep.saveAll(encodedStudents);
    }
    
    @Override
    public Student save(Student student) throws SQLException {
        return studentRep.save(new Student(student.getLogin(), passwordEnc.encode(student.getPasswordHash()),
                student.getFirstName(), student.getLastName(), student.getGroup()));
    }

    @Override
    public Student findByname(String name) throws SQLException {
        return studentRep.findByFirstName(name) ;
    }
}
