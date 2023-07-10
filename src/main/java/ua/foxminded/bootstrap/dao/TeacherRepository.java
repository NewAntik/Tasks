package ua.foxminded.bootstrap.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.foxminded.bootstrap.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t WHERE t.firstName = :firstName")
    Optional<Teacher> findByFirstName(@Param("firstName")String name);
    
    @Query("SELECT t FROM Teacher t JOIN t.specialization s WHERE s.name =:name ORDER BY t.id")
    List<Teacher> findBySpecialization(@Param("name")String specialization);
    
    @Query("SELECT t FROM Teacher t WHERE t.login = :login")
    Optional<Teacher> findByLogin(@Param("login")String login);
}
