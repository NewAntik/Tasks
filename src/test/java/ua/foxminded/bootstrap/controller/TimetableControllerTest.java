package ua.foxminded.bootstrap.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.bootstrap.models.Course;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.models.Room;
import ua.foxminded.bootstrap.models.Teacher;
import ua.foxminded.bootstrap.models.Timetable;
import ua.foxminded.bootstrap.service.TimetableService;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TimetableController.class})
class TimetableControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    TimetableService timetableServ;
    
    @Test
    void getTeacherTable_shouldShowListOfTeachers() throws Exception {
        when(timetableServ.findAll()).thenReturn(Arrays.asList(
                new Timetable(new Room("Lecture hall #1"), new Group("AA-01"), new Teacher("asama", "12345", "Gimmy", "Roll"), new Course("Math", "Math Description"), LocalDate.of(2023, 1, 1), 1L),
                new Timetable(new Room("Lecture hall #2"), new Group("AA-02"), new Teacher("rols", "54321", "Sins", "Jon"), new Course("Biology", "Biology Description"), LocalDate.of(2023, 1, 1), 2L)
        ));
        mvc.perform(get("/timetable"))
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
