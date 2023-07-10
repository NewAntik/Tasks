package ua.foxminded.bootstrap.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.service.CourseService;
import ua.foxminded.bootstrap.service.UserService;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {CourseController.class})
@Import(WebSecurityConfiguration.class)
class CourseControllerTest {

    @MockBean
    UserService userService;
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService courseServ;

    @Test
    void shouldRedirectToLoginPage() throws Exception {
        mvc.perform(get("/courses"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/register/login"));
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void shouldDenyAccessWithWrongRole() throws Exception {
        mvc.perform(get("/courses"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
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
