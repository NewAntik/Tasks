package ua.foxminded.bootstrap.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import ua.foxminded.bootstrap.models.Timetable;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {TimetableRepository.class}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/sql/clear_tables.sql", "/sql/sample_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TimetableRepositoryTest {

    @Autowired
    TimetableRepository timetableRep;

    @Test
    void findByStudentId_ShouldReturnTimetableByStudentId() {
        Timetable timetable = timetableRep.findByStudentId(2L);
        assertEquals(1, timetable.getId());
    }
    
    @Test
    void findByCourseId_ShouldReturnTimetableByCourseId() {
        Timetable timetable = timetableRep.findByCourseId(100L);
        assertEquals(1, timetable.getId());
    }
    
    @Test
    void findByTeacherId_ShouldReturnTimetableByTeacherId() {
        Timetable timetable = timetableRep.findByTeacherId(5L);
        assertEquals(5, timetable.getId());
    }
    
    @Test
    void findByRoomId_ShouldReturnTimetableByRoomId() {
        Timetable timetable = timetableRep.findByRoomId(3L);
        assertEquals(3, timetable.getId());
    }
    
    @Test
    void findByGroupId_ShouldReturnTimetableByGroupId() {
        Timetable timetable = timetableRep.findByGroupId(4L);
        assertEquals(4, timetable.getId());
    }
}
