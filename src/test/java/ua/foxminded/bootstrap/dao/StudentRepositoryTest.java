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
    StudentRepository studentRepository;
    
    @Test
    void findByLogin_ShouldReturnStudentWithSpecifiedLogin() {
        Student fromDb = studentRepository.findByLogin("student1").get();
        assertEquals("student1", fromDb.getLogin());
    }
    
    @Test
    void findByCourseName_ShouldReturnListOfStudenstReletedWithCourseByCourseName() {
        List<Student> students = studentRepository.findByCourseName("Math");
        assertEquals(2, students.size());
        assertEquals(2L, students.get(0).getId());
        assertEquals(4L, students.get(1).getId());
    }
    
    @Test
    void findByCourseId_ShouldReturnListOfStudenstReletedWithCourseByCourseId() {
        List<Student> students = studentRepository.findByCourseId(101L);
        assertEquals(2, students.size());
        assertEquals(2L, students.get(0).getId());
        assertEquals(4L, students.get(1).getId());
    }
    
    @Test
    void findByFirstName_ShouldReturnStudentByStudentFirstName() {
        Student student = studentRepository.findByFirstName("Jon").get();
        assertEquals("Jon", student.getFirstName());
        assertEquals(2L, student.getId());
    }
}
