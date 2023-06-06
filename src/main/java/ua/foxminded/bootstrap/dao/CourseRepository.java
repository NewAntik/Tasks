package ua.foxminded.bootstrap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.foxminded.bootstrap.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    @Query("SELECT c FROM Course c WHERE c.name = :name ORDER BY c.id")
    Course findByName(String name);
    
    @Query("SELECT c FROM Course c JOIN c.groups g JOIN g.students s WHERE s.id = :id")
    List<Course> findByStudentId(Long id);
}
