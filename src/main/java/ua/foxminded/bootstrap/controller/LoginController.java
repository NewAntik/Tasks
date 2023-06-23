package ua.foxminded.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        String role = determineUserRole(username, password);

        if (role.equals("ADMIN")) {
            return "redirect:/admin";
        } else if (role.equals("STUDENT")) {
            return "redirect:/students";
        } else if (role.equals("TEACHER")) {
            return "redirect:/teachers";
        }

        return "login";
    }
}
