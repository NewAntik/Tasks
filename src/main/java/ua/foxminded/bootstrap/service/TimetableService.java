package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Timetable;

public interface TimetableService {

    @Transactional(readOnly = true)
    List<Timetable> findAll();
    
    @Transactional(readOnly = true)
    List<Timetable> findByStudentId(Long studentId);
    
    @Transactional(readOnly = true)
    List<Timetable> findByTeacherId(Long teacherId);
    
    @Transactional
    List<Timetable> saveAll(List<Timetable> timetables) throws SQLException;
    
    @Transactional
    void delete(Long id) throws SQLException;
    
    @Transactional
    Timetable add(Long roomId, Long groupId, Long teacherId, Long courseId, LocalDate date, Long lessonNum) throws SQLException;
    
    @Transactional
    void update(Long scheduleId, Long roomId, Long groupId, Long teacherId, Long courseId, LocalDate date, Long lessonNum)throws SQLException;
}
