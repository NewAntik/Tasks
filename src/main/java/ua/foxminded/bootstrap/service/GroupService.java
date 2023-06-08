package ua.foxminded.bootstrap.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ua.foxminded.bootstrap.models.Group;

public interface GroupService {
    
    @Transactional
    List<Group> saveAll(List<Group> groups) throws SQLException;
    
    @Transactional(readOnly = true)
    List<Group> findAll() throws SQLException;
    
    @Transactional(readOnly = true)
    List<Group> findWithSpecifiedAmount(Long amount) throws SQLException;
}
