package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Student;
import ua.foxminded.bootstrap.service.StudentService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {StudentController.class})
class StudentControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    StudentService studentServ;
    
    @Test
    void getStudentTable_shouldShowListOfStudents() throws Exception {
        when(studentServ.findAll()).thenReturn(Arrays.asList(
               new Student("local", "12345", "Gosha", "Rast", new Group()),
               new Student("yammi", "54321", "Arnold", "Father", new Group())
        ));
        mvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Father")))
                .andExpect(content().string(containsString("Arnold")))
                .andExpect(content().string(containsString("Rast")))
                .andExpect(content().string(containsString("Gosha")));
    }
}
