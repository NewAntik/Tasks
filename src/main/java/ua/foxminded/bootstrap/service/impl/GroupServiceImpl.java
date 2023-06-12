package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    
    private GroupRepository groupRep;

    public GroupServiceImpl(GroupRepository groupRep) {
        this.groupRep = groupRep;
    }
    
    @Override
    public List<Group> findWithSpecifiedAmount(Long amount) throws SQLException {
        return groupRep.findByStudentNum(amount);
    }

    @Override
    public List<Group> findAll() throws SQLException {
        return groupRep.findAll();
    }

    @Override
    public List<Group> saveAll(List<Group> groups) throws SQLException {
        return groupRep.saveAll(groups);
    }

    
}
