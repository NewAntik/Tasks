package ua.foxminded.bootstrap.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.foxminded.bootstrap.models.Group;
import ua.foxminded.bootstrap.service.GroupService;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupServ;
    
    @GetMapping("/group")
    public String getGroupTable(Model model) throws SQLException {
        List<Group> groups = groupServ.findAll();
        model.addAttribute("groups", groups);
        
        return "groups/list-all";
    }
}
