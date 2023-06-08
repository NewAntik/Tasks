package ua.foxminded.bootstrap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.foxminded.bootstrap.models.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    
    @Query("SELECT t FROM Timetable t JOIN t.course c WHERE c.id =:id ORDER BY t.id")
    Timetable findByCourseId(@Param("id")Long id);
    
    @Query("SELECT t FROM Timetable t JOIN t.teacher a WHERE a.id =:id ORDER BY t.id")
    Timetable findByTeacherId(@Param("id")Long id);
    
    @Query("SELECT t FROM Timetable t JOIN t.room r WHERE r.id =:id ORDER BY t.id")
    Timetable findByRoomId(@Param("id")Long id);
    
    @Query("SELECT t FROM Timetable t JOIN t.group g WHERE g.id =:id ORDER BY t.id")
    Timetable findByGroupId(@Param("id")Long id);
    
    @Query("SELECT t FROM Timetable t JOIN t.group g JOIN g.students s WHERE s.id = :id")
    Timetable findByStudentId(@Param("id")Long id);
}
