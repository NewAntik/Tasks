package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.foxminded.bootstrap.dao.TeacherRepository;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.models.utils.Role;
import ua.foxminded.bootstrap.security.SecuritySharedConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {TeacherServiceImpl.class, SecuritySharedConfiguration.class})
class TeacherServiceImplTest {

    @MockBean
    TeacherRepository teacherRepository;
    
    @Autowired
    TeacherServiceImpl teacherService;

    @Test
    void findById_ShouldTrewIllegalArgumentExceptionTeacherDoesntExist() throws SQLException {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            teacherService.findById(1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void add_ShouldReturnListOfTeacher() throws SQLException {
        List<Teacher> teachers = new ArrayList<>(Arrays.asList(
                new Teacher("teacher", "teacher","teacher", "teacher")
            ));
        when(teacherRepository.findBySpecialization("Philosophy")).thenReturn(teachers);
        
        teacherService.findTeachersBySpecialization("Philosophy");
        
        verify(teacherRepository).findBySpecialization("Philosophy");
    }
    
    @Test
    void add_ShouldTrewIllegalArgumentExceptionSpecializationDoesntExist() {
        when(teacherRepository.findBySpecialization("Philosophy")).thenReturn(new ArrayList<>());

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            teacherService.findTeachersBySpecialization("Philosophy");
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void addTeacher_ShouldAddNewUserWithTeacherRole() throws SQLException {
        Teacher user = new Teacher("teacher1", "1234", "Teacher", "Teacher");
        when(teacherRepository.save(user)).thenReturn(user);
        
        Optional<Teacher> userAfterSave = teacherService.addNewTeacher("teacher1", "1234", "Teacher", "Teacher");
        
        verify(teacherRepository).save(user);
        assertEquals(Role.TEACHER, userAfterSave.get().getRole());
    }
    
    @Test
    void addTeacher_ShouldThrewIllegalArgumentExceptionTeacherAlreadyExist() {
        when(teacherRepository.findByLogin("teacher1")).thenReturn(Optional.of(new Teacher("teacher1", "1234", "Teacher", "Teacher")));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            teacherService.addNewTeacher("teacher1", "1234", "Teacher", "Teacher");
        });
        assertNotNull(thrown.getMessage());
    }
}
