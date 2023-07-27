package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.service.TeacherService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {TeacherController.class})
class TeacherControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    TeacherService teacherServ;
    
    @Test
    @WithMockUser(roles = "TEACHER")
    void showStudents_ShouldSowListOfStudentReletedWiSpecifiedGroup() throws Exception { 
        Long teacherId = 1L;
        Set<Course> specializations = new HashSet<>();
        specializations.add(new Course("Math", "Math Description"));

        when(teacherServ.findById(teacherId)).thenReturn(new Teacher("teacher1", "1234", "Yakof", "Jorson", specializations));

        mvc.perform(get("/specialisations/{teacherId}", teacherId))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers/its-courses"))
                .andExpect(model().attributeExists("teacherFirstName", "specializations"))
                .andExpect(model().attribute("specializations", specializations));
    }
    
    @Test
    @WithMockUser(roles = "TEACHER")
    void getTeacherTable_shouldShowListOfTeachers() throws Exception {
        when(teacherServ.findAll()).thenReturn(Arrays.asList(
                new Teacher("local", "12345", "Gosha", "Rast"),
                new Teacher("yammi", "54321", "Arnold", "Father")
        ));
        mvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Gosha")))
                .andExpect(content().string(containsString("Rast")))
                .andExpect(content().string(containsString("Arnold")))
                .andExpect(content().string(containsString("Father")));
    }
}
