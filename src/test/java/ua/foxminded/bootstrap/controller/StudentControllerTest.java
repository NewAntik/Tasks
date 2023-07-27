package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ua.foxminded.bootstrap.security.WebSecurityConfiguration;


import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.service.StudentService;
import ua.foxminded.bootstrap.service.UserService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = {StudentController.class})
@Import(WebSecurityConfiguration.class)
class StudentControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    StudentService studentServ;
    
    @MockBean
    UserService userService;

    @Test
    @WithMockUser(roles = "STUDENT")
    void getStudentTable_shouldShowListOfStudents() throws Exception {
        when(studentServ.findAll()).thenReturn(Arrays.asList(
               new Student("local", "12345", "Gosha", "Rast", new Group()),
               new Student("yammi", "54321", "Arnold", "Father", new Group())
        ));
        mvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Father")))
                .andExpect(content().string(containsString("Arnold")))
                .andExpect(content().string(containsString("Rast")))
                .andExpect(content().string(containsString("Gosha")));
    }
    
    @Test
    @WithMockUser(roles = "TEACHER")
    void shouldDenyAccessWithWrongRole() throws Exception {
        mvc.perform(get("/welcome-student"))
                .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(roles = "STUDENT")
    void getStudentWelcome_shouldShowStudentWelcomePage() throws Exception {
        mvc.perform(get("/welcome-student"))
                .andExpect(status().isOk())
                .andExpect(view().name("students/welcome"));
    }
}
