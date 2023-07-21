package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Group;

public interface GroupService {
    
    @Transactional
    List<Group> saveAll(List<Group> groups) throws SQLException;
    
    @Transactional(readOnly = true)
    List<Group> findAll() throws SQLException;
    
    @Transactional(readOnly = true)
    List<Group> findWithSpecifiedAmount(Long amount) throws SQLException;
    
    @Transactional
    Optional<Group> addNewGroup(String name) throws SQLException;
    
    @Transactional
    Optional<Group> deleteGroupById(Long id) throws SQLException;
    
    @Transactional
    Optional<Group> updateGroup(Long id, String newName) throws SQLException;

    @Transactional
    void reassignStudentToNewGroup(Long studentId, Long groupId, Long relatedGroupId);

    @Transactional
    Group getGroupById(Long groupId);
}
