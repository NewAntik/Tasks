package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.security.WebSecurityConfiguration;
import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Room;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.models.Timetable;
import ua.foxminded.bootstrap.service.TimetableService;
import ua.foxminded.bootstrap.service.UserService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = {TimetableController.class})
@Import(WebSecurityConfiguration.class)
class TimetableControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    TimetableService timetableServ;
    
    @MockBean
    UserService userService;
    
    @Test
    @WithMockUser(roles = "STAFF")
    void updateSchedule_ShouldUpdateSchedule() throws Exception {
        String formattedDate = LocalDate.of(2023, 6, 22).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        mvc.perform(post("/create-new-schedule")
                .param( "scheduleId", "1")
                .param( "groupId", "1")
                .param( "teacherId", "1")
                .param( "roomId", "1")
                .param( "courseId", "1")
                .param( "lessonNum", "1")
                .param( "date", formattedDate))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void saveNewSchedule_ShouldSaveNewSchedule() throws Exception {
        String formattedDate = LocalDate.of(2023, 6, 22).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        mvc.perform(post("/create-new-schedule")
                .param( "groupId", "1")
                .param( "teacherId", "1")
                .param( "roomId", "1")
                .param( "courseId", "1")
                .param( "lessonNum", "1")
                .param( "date", formattedDate))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "STAFF")
    void deleteSchedule_ShouldDeleteSchedule() throws Exception {
        mvc.perform(post("/delete-exist-schedule")
                .param( "scheduleId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("successMessage"));
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void getTeacherTable_shouldShowListOfTeachers() throws Exception {
        when(timetableServ.findAll()).thenReturn(Arrays.asList(
                new Timetable(new Room("Lecture hall #1"), new Group("AA-01"), new Teacher("asama", "12345", "Gimmy", "Roll"), new Course("Math", "Math Description"), LocalDate.of(2023, 1, 1), 1L),
                new Timetable(new Room("Lecture hall #2"), new Group("AA-02"), new Teacher("rols", "54321", "Sins", "Jon"), new Course("Biology", "Biology Description"), LocalDate.of(2023, 1, 1), 2L)
        ));
        mvc.perform(get("/timetables"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Lecture hall #1")))
                .andExpect(content().string(containsString("AA-01")))
                .andExpect(content().string(containsString("Gimmy")))
                .andExpect(content().string(containsString("Math")))
                .andExpect(content().string(containsString("Lecture hall #2")))
                .andExpect(content().string(containsString("AA-02")))
                .andExpect(content().string(containsString("Sins")))
                .andExpect(content().string(containsString("Biology")));
    }
}
