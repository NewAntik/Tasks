package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    
    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRep) {
        this.groupRepository = groupRep;
    }
    
    @Override
    public List<Group> findWithSpecifiedAmount(Long amount) throws SQLException {
        return groupRepository.findByStudentNum(amount);
    }

    @Override
    public List<Group> findAll() throws SQLException {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> saveAll(List<Group> groups) throws SQLException {
        return groupRepository.saveAll(groups);
    }

    
}
