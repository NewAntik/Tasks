package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.service.GroupService;

@Controller
public class GroupController {
    private static final String ERROR = "errorMessage";
    private static final String SUCCESS_MESSAGE = "successMessage";
    private static final String SUCCESS = "Success!";

    private final GroupService groupService;
    
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    
    @GetMapping("/students/{groupId}")
    public String showStudents(@PathVariable Long groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        try {
            if(group.getStudents().isEmpty()) {
                throw new IllegalArgumentException("This reletion student to group doesn't exist!");
            } else {
                model.addAttribute("groupName", group.getName());
                model.addAttribute("students", group.getStudents());
                
                return "groups/relation-students";
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
                
        return "groups/list-all";
    }
    
    @PostMapping("/reassign-student-to-group")
    public String reassignStudentToNewGroup(@RequestParam("studentId") Long studentId, @RequestParam("newGroupId") Long newGroupId,@RequestParam("relatedGroupId") Long relatedGroupId, Model model) throws SQLException {
        try {
            groupService.reassignStudentToNewGroup(studentId, newGroupId, relatedGroupId);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "groups/reassign";
    }
    
    @GetMapping("/reassign-student")
    public String reassignStudentPage() throws SQLException {
        return "groups/reassign";
    }
    
    @GetMapping("/assign-student")
    public String assignStudentPage() throws SQLException {
        return "groups/assign";
    }
    
    @GetMapping("/add-group")
    public String addNewGroup() throws SQLException {
        return "groups/add";
    }
    
    @GetMapping("/delete-group")
    public String deleteGroup() throws SQLException {
        return "groups/delete";
    }
    
    @GetMapping("/update-group")
    public String updateGroup() throws SQLException {
        return "groups/update";
    }
    
    @PostMapping("/update-group-by-id")
    public String updateGroup(@RequestParam("id") Long id, @RequestParam("newName") String newName, Model model) throws SQLException {
        try {
            groupService.updateGroup(id, newName);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "groups/update";
    }
    
    @PostMapping("/delete-group-by-id")
    public String deleteGroup(@RequestParam("id") Long id, Model model) throws SQLException {
        try {
            groupService.deleteGroupById(id);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "groups/delete";
    }
    
    @PostMapping("/save-group")
    public String saveNewGroup(@RequestParam("name") String name, Model model) throws SQLException {
        try {
            groupService.addNewGroup(name);
            model.addAttribute(SUCCESS_MESSAGE, SUCCESS);
        } catch (IllegalArgumentException e) {
            model.addAttribute(ERROR, e.getMessage());
        }
        
        return "groups/add";
    }
    
    @GetMapping("/groups")
    public String getGroupTable(Model model) throws SQLException {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        
        return "groups/list-all";
    }
}
