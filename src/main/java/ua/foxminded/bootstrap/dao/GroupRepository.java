package ua.foxminded.bootstrap.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.foxminded.bootstrap.models.Group;

@Repository
public interface GroupRepository extends JpaRepository <Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.name = :name")
    Group findByName(String name);
    
    @Query("SELECT g FROM Group g WHERE SIZE(g.students) <= :num ORDER BY g.id")
    List<Group> findByStudentNum(Long num);

    @Query("SELECT g FROM Group g JOIN g.students s WHERE g.id = :groupId AND s.id = :studentId")
    Optional<Group> checkRelationStudentGroup(@Param("studentId") Long studentId, @Param("groupId") Long groupId);
}
