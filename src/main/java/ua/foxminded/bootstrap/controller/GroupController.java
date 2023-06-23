package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.service.GroupService;

@Controller
public class GroupController {

    private final GroupService groupServ;
    
    public GroupController(GroupService groupService) {
        this.groupServ = groupService;
    }
    
    @GetMapping("/group")
    public String getGroupTable(Model model) throws SQLException {
        List<Group> groups = groupServ.findAll();
        model.addAttribute("groups", groups);
        
        return "groups/list-all";
    }
}
