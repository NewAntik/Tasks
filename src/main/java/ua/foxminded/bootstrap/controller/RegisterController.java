package ua.foxminded.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/register/logout";
    }
}
