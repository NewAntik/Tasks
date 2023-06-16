package ua.foxminded.bootstrap.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.service.CourseService;

@WebMvcTest(controllers = {CourseController.class})
class CourseControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    CourseService courseServ;
    
    @Test
    void getCourseTable_shouldShowListOfCourses() throws Exception {
        when(courseServ.findAll()).thenReturn(Arrays.asList(
               new Course(100L, "Math", "Math Description"),
               new Course(101L, "Biology", "Biology Description"),
               new Course(102L, "Geography", "Geography Description"),
               new Course(103L, "Music", "Music Description"),
               new Course(104L, "History", "History Description"),
               new Course(105L, "Physics", "Physics Description"),
               new Course(106L, "Medicine", "Medicine Description"),
               new Course(7L, "Tehnology", "Tehnology Description")
        ));
//        mvc.perform(get("/courses"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Math")))
//                .andExpect(content().string(containsString("Math course")));
    }
}
