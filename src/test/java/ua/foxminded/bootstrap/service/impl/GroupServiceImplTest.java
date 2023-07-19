
package ua.foxminded.bootstrap.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.foxminded.bootstrap.dao.GroupRepository;
import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.service.GroupService;

@SpringBootTest(classes = {GroupServiceImpl.class})
class GroupServiceImplTest {
    
    @MockBean
    GroupRepository groupRepository;
    
    @Autowired
    GroupService groupServiceImpl;
    
    private Group group;
    
    @BeforeEach
    void addData() {
        group = new Group (1L, "Name");
    }
    
    @Test
    void updateCourse_ShouldUpdateExistsCourse() throws SQLException {
        Group updatedGroup = new Group (1L, "Updated Name");
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        
        groupServiceImpl.updateGroup(group.getId(), "Updated Name");
        
        verify(groupRepository).save(updatedGroup);
    }
    
    @Test
    void updateCourse_ShouldThrewIllegalArgumentExceptionCourseDoesntExist() {
        when(groupRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            groupServiceImpl.updateGroup(1L, "Name");
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void deleteCourseById_ShouldThrewIllegalArgumentExceptionCourseDoesntExist() {
        when(groupRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            groupServiceImpl.deleteGroupById(1L);
        });
        assertNotNull(thrown.getMessage());
    }
    
    @Test
    void deleteCourseById_ShouldAddDeleteCourse() throws SQLException {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        
        groupServiceImpl.deleteGroupById(1L);
        verify(groupRepository).delete(group);
    }
       
    
    @Test
    void addNewGroup_ShouldAddNewGroup() throws SQLException {
        Group group = new Group ("Name");
        when(groupRepository.findByName("Name")).thenReturn(null);
        when(groupRepository.save(group)).thenReturn(group);
         
        Optional<Group> afterSave = groupServiceImpl.addNewGroup("Name");
        
        verify(groupRepository).save(group);
        assertEquals(group, afterSave.get());
    }
    
    @Test
    void addNewGroup_ShouldThrewIllegalArgumentExceptionCourseAlreadyExist() {
        when(groupRepository.findByName("Name")).thenReturn(group);

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            groupServiceImpl.addNewGroup("Name");
        });
        assertNotNull(thrown.getMessage());
    }
}
