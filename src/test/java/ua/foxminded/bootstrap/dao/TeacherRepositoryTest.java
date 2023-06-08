package ua.foxminded.bootstrap.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import ua.foxminded.bootstrap.models.Teacher;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {TeacherRepository.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository teacherRep;
    
    @Test
    void findBySpecialization_ShouldReturnListOfTeachersBySpecialization() {
        List<Teacher> teachers = teacherRep.findBySpecialization("Math");
        assertEquals(2, teachers.size());
        assertEquals(3L, teachers.get(0).getId());
        assertEquals(5L, teachers.get(1).getId());
    }
    
    @Test
    void findByFirstName_ShouldReturnTeacherByTeacherFirstName() {
        Teacher teacher = teacherRep.findByFirstName("Ann");
        assertEquals("Ann", teacher.getFirstName());
        assertEquals(3L, teacher.getId());
    }
}
