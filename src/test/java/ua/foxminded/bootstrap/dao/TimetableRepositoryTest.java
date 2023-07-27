package ua.foxminded.bootstrap.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    void findByGroupLessonData_ShouldReturnTimetableByGroupLessonData() {
        Optional<Timetable> timetable = timetableRep.findByGroupLessonData(3L, 3l, LocalDate.of(2023, 06, 23));
        assertEquals(3, timetable.get().getId());
    }
    
    @Test
    void findByCourseLessonData_ShouldReturnTimetableByCourseLessonData() {
        Optional<Timetable> timetable = timetableRep.findByCourseLessonData(100L, 5l, LocalDate.of(2023, 06, 22));
        assertEquals(1, timetable.get().getId());
    }
    
    @Test
    void findByTeacherLessonData_ShouldReturnTimetableByTeacherLessonData() {
        Optional<Timetable> timetable = timetableRep.findByTeacherLessonData(3L, 5l, LocalDate.of(2023, 06, 22));
        assertEquals(1, timetable.get().getId());
    }
    
    @Test
    void findByRoomLessonData_ShouldReturnTimetableByRoomLessonData() {
        Optional<Timetable> timetable = timetableRep.findByRoomLessonData(1L, 5l, LocalDate.of(2023, 06, 22));
        assertEquals(1, timetable.get().getId());
    }

    @Test
    void findByStudentId_ShouldReturnTimetableByStudentId() {
       List<Timetable> timetables = timetableRep.findByStudentId(2L);
        assertEquals(2, timetables.size());
    }
    
    @Test
    void findByCourseId_ShouldReturnTimetableByCourseId() {
        Timetable timetable = timetableRep.findByCourseId(100L);
        assertEquals(1, timetable.getId());
    }
    
    @Test
    void findByTeacherId_ShouldReturnTimetableByTeacherId() {
        List<Timetable> timetables = timetableRep.findByTeacherId(3L);
        assertEquals(2, timetables.size());
    }
    
    @Test
    void findByRoomId_ShouldReturnTimetableByRoomId() {
        Timetable timetable = timetableRep.findByRoomId(3L);
        assertEquals(3, timetable.getId());
    }
    
    @Test
    void findByGroupId_ShouldReturnTimetableByGroupId() {
        Timetable timetable = timetableRep.findByGroupId(3L);
        assertEquals(3, timetable.getId());
    }
}
