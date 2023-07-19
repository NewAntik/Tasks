package ua.foxminded.bootstrap.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Group> addNewGroup(String name) throws SQLException {
        if (groupRepository.findByName(name) == null) {
            return Optional.of(groupRepository.save(new Group(name)));
        } else {
            throw new IllegalArgumentException("Group with this name \"" + name + "\" already exists!");
        }
    }

    @Override
    public Optional<Group> deleteGroupById(Long id) throws SQLException {
        Group groupForDelete = getGroupById(id);
        groupRepository.delete(groupForDelete);
        
        return Optional.of(groupForDelete);
    }

    @Override
    public Optional<Group> updateGroup(Long id, String newName) throws SQLException {
        Group groupForSave = getGroupById(id);
        groupRepository.save(new Group(groupForSave.getId(), newName));
        
        return Optional.of(groupForSave);
    }
    
    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(
                () -> new IllegalArgumentException("Group with this id \"" + groupId + "\" doesn't exist!"));
    }
}
