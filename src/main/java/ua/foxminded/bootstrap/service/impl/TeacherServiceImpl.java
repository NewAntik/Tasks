package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.TeacherRepository;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

    private final PasswordEncoder passwordEnc;
    private final TeacherRepository teacherRep;
    
    public TeacherServiceImpl(TeacherRepository teacherRepisitory, PasswordEncoder passwordEncoder) {
        this.teacherRep = teacherRepisitory;
        this.passwordEnc = passwordEncoder;
    }
    
    @Override
    public List<Teacher> saveAll(List<Teacher> teachers) throws SQLException {
        List<Teacher> encodedTeachers = teachers.stream()
                .map(teacher -> new Teacher(teacher.getLogin(), passwordEnc.encode(teacher.getPasswordHash()), teacher.getFirstName(), teacher.getLastName()))
                .collect(Collectors.toList());
        
        return teacherRep.saveAll(encodedTeachers);
    }

    @Override
    public List<Teacher> findTeachersBySpecialization(String specialization) throws SQLException {
        if(teacherRep.findBySpecialization(specialization).isEmpty()) {
            throw new IllegalArgumentException("Teachers with this specialization" + specialization + "don't exist!");
        } else {
            return teacherRep.findBySpecialization(specialization);
        }
    }

    @Override
    public Teacher add(String login, String passwordHash, String firstName, String lastName) throws SQLException {
        return teacherRep.save(new Teacher(login, passwordHash, firstName, lastName));
    }

    @Override
    public void delete(Long id) throws SQLException {
        teacherRep.deleteById(id);
    }

    @Override
    public List<Teacher> findAll() throws SQLException {
        return teacherRep.findAll();
    }

    @Override
    public Teacher findByName(String username) {
        return teacherRep.findByFirstName(username);
    }

    @Override
    public Teacher save(Teacher teacher) throws SQLException {
        return new Teacher(teacher.getLogin(), passwordEnc.encode(teacher.getPasswordHash()), teacher.getFirstName(), teacher.getLastName());
    }
}
