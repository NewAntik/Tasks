package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.foxminded.bootstrap.dao.TeacherRepository;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.security.SecuritySharedConfiguration;

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
}
