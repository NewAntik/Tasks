package ua.foxminded.bootstrap.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.service.CourseService;
import ua.foxminded.bootstrap.dao.CourseRepository;

@SpringBootTest(classes = {CourseServiceImpl.class})
class CourseServiceImplTest {

    @MockBean
    CourseRepository courseRepository;
    
    @Autowired
    CourseService courseServiceImpl;
    
    private Course course;
    
    @BeforeEach
    void addData() {
        course = new Course (1L, "Name", "Description");
    }
    
    @Test
    void updateCourse_ShouldUpdateExistsCourse() throws SQLException {
        Course updatedCourse = new Course (1L, "Updated Name", "Updated Description");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        
        courseServiceImpl.updateCourse(course.getId(), "Updated Name", "Updated Description");
        
        verify(courseRepository).save(updatedCourse);
    }
    
    @Test
    void updateCourse_ShouldThrewIllegalArgumentExceptionCourseDoesntExist() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.updateCourse(1L, "Name", "Description");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void deleteCourseById_ShouldThrewIllegalArgumentExceptionCourseDoesntExist() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.deleteCourseById(1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void deleteCourseById_ShouldAddDeleteCourse() throws SQLException {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        
        courseServiceImpl.deleteCourseById(1L);
        verify(courseRepository).delete(course);
    }
    
    @Test
    void addNewCourse_ShouldAddNewCourse() throws SQLException {
        Course course = new Course ("Name", "Description");
        when(courseRepository.findByName("Name")).thenReturn(null);
        when(courseRepository.save(course)).thenReturn(course);
         
        Optional<Course> afterSave = courseServiceImpl.addNewCourse("Name", "Description");
        verify(courseRepository).save(course);
        assertEquals(course, afterSave.get());
    }
    
    @Test
    void addNewCourse_ShouldThrewIllegalArgumentExceptionCourseAlreadyExist() {
        when(courseRepository.findByName("Name")).thenReturn(course);

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.addNewCourse("Name", "Description");
        });
        assertNotNull(thrown.getMessage());
    }
}
