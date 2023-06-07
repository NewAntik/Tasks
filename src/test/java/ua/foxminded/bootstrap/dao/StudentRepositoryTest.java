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

import ua.foxminded.bootstrap.models.Student;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {StudentRepository.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRep;
    
    @Test
    void findByCourseName_ShouldReturnListOfStudenstReletedWithCourseByCourseName() {
        List<Student> students = studentRep.findByCourseName("Math");
        assertEquals(2, students.size());
        assertEquals(2L, students.get(0).getId());
        assertEquals(4L, students.get(1).getId());
    }
    
    @Test
    void findByCourseId_ShouldReturnListOfStudenstReletedWithCourseByCourseId() {
        List<Student> students = studentRep.findByCourseId(101L);
        assertEquals(2, students.size());
        assertEquals(2L, students.get(0).getId());
        assertEquals(4L, students.get(1).getId());
    }
    
    @Test
    void findByFirstName_ShouldReturnStudentByStudentFirstName() {
        Student student = studentRep.findByFirstName("Jon");
        assertEquals("Jon", student.getFirstName());
        assertEquals(2L, student.getId());
    }
}
