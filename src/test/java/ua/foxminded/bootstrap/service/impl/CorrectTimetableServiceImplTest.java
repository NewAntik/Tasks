package ua.foxminded.bootstrap.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.bootstrap.dao.*;
import ua.foxminded.bootstrap.models.*;
import ua.foxminded.bootstrap.service.TimetableService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TimetableServiceImpl.class})
public class CorrectTimetableServiceImplTest {

    @MockBean
    TimetableRepository timetableRepository;
    @MockBean
    RoomRepository roomRepository;
    @MockBean
    GroupRepository groupRepository;
    @MockBean
    TeacherRepository teacherRepository;
    @MockBean
    CourseRepository courseRepository;

    @Autowired
    TimetableService timetableService;

    @Test
    void add_ShouldAddNewTimetable() throws SQLException {
        when(roomRepository.findById(7L)).thenReturn(Optional.of(new Room(7L, "")));
        when(groupRepository.findById(7L)).thenReturn(Optional.of(new Group(7L, "", Collections.emptySet(), Collections.emptySet())));
        when(teacherRepository.findById(7L)).thenReturn(Optional.of(new Teacher(7L, "", "", "", "")));
        when(courseRepository.findById(7L)).thenReturn(Optional.of(new Course(7L, "", "")));

        when(timetableRepository.save(any())).thenAnswer(call -> call.getArgument(0, Timetable.class));

        Timetable actual = timetableService.add(7L, 7L, 7L, 7L, LocalDate.of(2023, 1, 1), 5L);
        assertEquals(actual.getTeacher().getId(), 7);
    }
}
