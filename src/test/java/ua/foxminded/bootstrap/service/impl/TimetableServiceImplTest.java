package ua.foxminded.bootstrap.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.bootstrap.dao.TeacherRepository;
import ua.foxminded.bootstrap.models.Timetable;
import ua.foxminded.bootstrap.dao.RoomRepository;
import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.dao.CourseRepository;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {RoomRepository.class,CourseRepository.class,GroupRepository.class,TeacherRepository.class, TimetableServiceImpl.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TimetableServiceImplTest {

    @Autowired
    TimetableServiceImpl timetableServ;
    
    @Test
    @Sql(scripts = {"/sql/clear_tables.sql","/sql/sample_data_without_timetable.sql"})
    void add_ShouldAddNewTimetable() throws SQLException {
        Timetable actual = timetableServ.add(7L, 7L, 7L, 7L, LocalDate.of(2023, 6, 8), 5L);
        assertEquals(actual.getTeacher().getId(), 7);
    }
    
    @Test
    void add_ShouldTrewIllegalArgumentExceptionRoomDoesntExist() {
        
        Throwable thrown = assertThrows(IllegalArgumentException.class,() ->{
            timetableServ.add(6L, 1L, 1L, 1L, LocalDate.of(2016, 9, 23), 1L);  
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void add_ShouldTrewIllegalArgumentExceptionGroupDoesntExist() {
        
        Throwable thrown = assertThrows(IllegalArgumentException.class,() ->{
            timetableServ.add(1L, 6L, 1L, 1L, LocalDate.of(2016, 9, 23), 1L);    
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void add_ShouldTrewIllegalArgumentExceptionTeacherDoesntExist() {
        
        Throwable thrown = assertThrows(IllegalArgumentException.class,() ->{
            timetableServ.add(1L, 1L, 1L, 1L, LocalDate.of(2016, 9, 23), 1L);    
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void add_ShouldTrewIllegalArgumentExceptionCourseDoesntExist() {
        String messege = "Course with this id " + "1" + " doesn't exist!";
        Throwable thrown = assertThrows(IllegalArgumentException.class,() ->{
            timetableServ.add(1L, 1L, 3L, 1L, LocalDate.of(2016, 9, 23), 1L);     
        });
        assertEquals(messege, thrown.getMessage());
    }
}
