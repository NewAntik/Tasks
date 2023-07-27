package ua.foxminded.bootstrap.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;

import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.dao.StudentRepository;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.models.utils.Role;
import ua.foxminded.bootstrap.security.SecuritySharedConfiguration;

@SpringBootTest(classes = { StudentServiceImpl.class, SecuritySharedConfiguration.class })
class StudentServiceImplTest {

    @MockBean
    GroupRepository groupRepository;

    @MockBean
    StudentRepository studentRepository;

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Test
    void findById_ShouldThrewIllegalArgumentExceptionStudentDoesntExist() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.findById(1L);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void addStudent_ShouldAddNewStudentWithStudentRole() throws SQLException {
        Group group = new Group(1L, "AA-01", new HashSet<>(), new HashSet<>());
        Student user = new Student("student1", "1234", "Student", "Student", group);
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(studentRepository.save(user)).thenReturn(user);

        Optional<Student> studentAfterSave = studentServiceImpl.addNewStudent("teacher1", "1234", "Teacher", "Teacher",
                1L);

        verify(studentRepository).save(user);
        assertEquals(Role.STUDENT, studentAfterSave.get().getRole());
    }

    @Test
    void addStudent_ShouldThrewIllegalArgumentExceptionGroupDoesntExist() {
        when(groupRepository.findById(1L)).thenReturn(Optional.empty());
        
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            studentServiceImpl.addNewStudent("student1", "1234", "Student", "Student", 1L);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void addStudent_ShouldThrewIllegalArgumentExceptionStudentAlreadyExist() {
        when(studentRepository.findByLogin("student1")).thenReturn(Optional.of(new Student("student1", "1234", "Student", "Student", new Group())));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.addNewStudent("student1", "1234", "Student", "Student", 1L);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void addStudent_ShouldThrewIllegalArgumentExceptionGroupIdEqualsNull() {
        when(studentRepository.findByLogin("student1")).thenReturn(Optional.of(new Student("student1", "1234", "Student", "Student", new Group())));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.addNewStudent("student1", "1234", "Student", "Student", null);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void addStudent_ShouldThrewIllegalArgumentExceptionGroupIdEqualsZero() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.addNewStudent("student1", "1234", "Student", "Student", 0L);
        });
        assertNotNull(thrown.getMessage());
    }
}
