package ua.foxminded.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.foxminded.bootstrap.service.UserService;

@Controller
public class AdminController {
    
    private final UserService userServ;

    public AdminController(UserService userServ) {
        this.userServ = userServ;
    }

    @GetMapping("/welcome-admin")
    public String getAdminWelcome(){
        return "admin/welcome";
    }
    
    @GetMapping("/create-user")
    public String createNewUser() {
        return "/admin/create-user";
    }
    
    @PostMapping("/save-user")
    public String saveNewUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("login") String login,
                              @RequestParam("password") String password, @RequestParam("role") String role, Model model) {
        try {
            userServ.addUser(firstName, lastName, login, password, role);
            model.addAttribute("successMessage", "Success!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "User with this login already exists");
        }
        return "admin/create-user";
    }
}
