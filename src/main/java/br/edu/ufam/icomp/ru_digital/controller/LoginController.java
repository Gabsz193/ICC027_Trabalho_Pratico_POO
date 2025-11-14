package br.edu.ufam.icomp.ru_digital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit() {
        // Redireciona para a home APÓS o login (simulação)
        return "redirect:/home";
    }
}