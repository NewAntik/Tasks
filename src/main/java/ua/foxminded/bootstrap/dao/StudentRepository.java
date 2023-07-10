package ua.foxminded.bootstrap.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.foxminded.bootstrap.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.firstName = :firstName")
    Optional<Student> findByFirstName(@Param("firstName")String name);
    
    @Query("SELECT s FROM Student s left JOIN FETCH s.group g left JOIN FETCH g.courses c WHERE c.id = :id ORDER BY s.id")
    List<Student> findByCourseId(@Param("id") Long courseId);
    
    @Query("SELECT s FROM Student s left JOIN FETCH s.group g left JOIN FETCH g.courses c WHERE c.name = :name ORDER BY s.id")
    List<Student> findByCourseName(String name);
    
    @Query("SELECT s FROM Student s WHERE s.login = :login")
    Optional<Student> findByLogin(@Param("login")String login);
}
