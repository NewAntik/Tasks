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
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.service.CourseService;
import ua.foxminded.bootstrap.dao.CourseRepository;
import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.dao.TeacherRepository;

@SpringBootTest(classes = {CourseServiceImpl.class})
class CourseServiceImplTest {

    @MockBean
    GroupRepository groupRepository;
    
    @MockBean
    TeacherRepository teacherRepository;
    
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
    void reassignGroupToCourse__ShouldThrewIllegalArgumentExceptionReletionGroupToCourseDoesntExist() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(new Group("AA-01")));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.findRelationGroupCourse(1L, 1L)).thenReturn(Optional.empty());
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.reassignGroupToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void reassignTeacherToCourse__ShouldThrewIllegalArgumentExceptionReletionTeacherToCourseDoesntExist() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(new Teacher(1L, "login", "password", "Arnold", "Logan")));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.findRelationTeacherCourse(1L, 1L)).thenReturn(Optional.empty());

        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.reassignTeacherToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void assignGroupToCourse__ShouldThrewIllegalArgumentExceptionReletionGroupToCourseAlreadyExist() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(new Group("AA-01")));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.findRelationGroupCourse(1L, 1L)).thenReturn(Optional.of(course));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.assignGroupToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void assignTeacherToCourse__ShouldThrewIllegalArgumentExceptionReletionTeacherToCourseAlreadyExist() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(new Teacher(1L, "login", "password", "Arnold", "Logan")));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.findRelationTeacherCourse(1L, 1L)).thenReturn(Optional.of(course));

        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.assignTeacherToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void reassignTeacherToCourse__ShouldThrewIllegalArgumentExceptionTeacherDoesntExist() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.reassignTeacherToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void reassignTeacherToCourse__ShouldThrewIllegalArgumentExceptionCourseDoesntExist() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(new Teacher(1L, "login", "password", "Arnold", "Logan")));
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.reassignTeacherToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void reassignTeacherToCourse__ShouldReassignTeacherToCourse() throws SQLException {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(new Teacher(1L, "login", "password", "Arnold", "Logan")));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.findRelationTeacherCourse(1L, 1L)).thenReturn(Optional.of(course));

        courseServiceImpl.reassignTeacherToCourse(1L, 1L);
        verify(courseRepository).save(course);
    }
    
    @Test
    void reassignGroupToCourse__ShouldThrewIllegalArgumentExceptionGroupDoesntExist() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.reassignGroupToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void reassignGroupToCourse__ShouldThrewIllegalArgumentExceptionCourseDoesntExist() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(new Group("AA-01")));
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.reassignGroupToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void reassignGroupToCourse__ShouldReassignGroupToCourse() throws SQLException {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(new Group("AA-01")));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.findRelationGroupCourse(1L, 1L)).thenReturn(Optional.of(course));
        
        courseServiceImpl.reassignGroupToCourse(1L, 1L);
        verify(courseRepository).save(course);
    }
    
    @Test
    void assignGroupToCourse_ShouldThrewIllegalArgumentExceptionGroupDoesntExist() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.assignGroupToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void assignGroupToCourse_ShouldThrewIllegalArgumentExceptionCourseDoesntExist() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(new Group("AA-01")));
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.assignGroupToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void assignGroupToCourse_ShouldAssignGroupToCourse() throws SQLException {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(new Group("AA-01")));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        courseServiceImpl.assignGroupToCourse(1L, 1L);
        verify(courseRepository).save(course);
    }
    
    @Test
    void assignTeacherToCourse_ShouldThrewIllegalArgumentExceptionTeacherDoesntExist() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.assignTeacherToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void assignTeacherToCourse_ShouldThrewIllegalArgumentExceptionCourseDoesntExist() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(new Teacher(1L, "login", "password", "Arnold", "Logan")));
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            courseServiceImpl.assignTeacherToCourse(1L, 1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void assignTeacherToCourse_ShouldAssignTeacherToCourse() throws SQLException {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(new Teacher(1L, "login", "password", "Arnold", "Logan")));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        courseServiceImpl.assignTeacherToCourse(1L, 1L);
        verify(courseRepository).save(course);
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
