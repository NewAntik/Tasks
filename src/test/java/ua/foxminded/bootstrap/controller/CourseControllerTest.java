package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.service.CourseService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
               new Course(103L, "Music", "Music Description"),
               new Course(105L, "Physics", "Physics Description")
        ));
        mvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Physics")))
                .andExpect(content().string(containsString("Physics Description")))
                .andExpect(content().string(containsString("Music")))
                .andExpect(content().string(containsString("Music Description")))
                .andExpect(content().string(containsString("Math")))
                .andExpect(content().string(containsString("Math Description")));
    }
}
