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

import ua.foxminded.bootstrap.models.Course;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {CourseRepository.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = { "/sql/clear_tables.sql", "/sql/sample_data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRep;
    
    @Test
    void findByStudentId_ShouldReturnListOfCourseReletedWithStudentByStudentIdOrderedById() {
        List<Course> courses = courseRep.findByStudentId(5L);
        assertEquals(new Course(100L, "Math", null), courses.get(0));
        assertEquals(new Course(101L, "Biology", null), courses.get(1));
        assertEquals(new Course(102L, "Geography", null), courses.get(2));
    }
    
    @Test
    void findByName_ShouldReturnCourseByName() {
        Course medicine = courseRep.findByName("Medicine");
        assertEquals("Medicine", medicine.getName());
        assertEquals(106L, medicine.getId());
    }
}
