package com.FinTrack.controller;

import com.FinTrack.model.User;
import com.FinTrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        // Recupera ou cria um objeto User baseado no usuário autenticado
        User user = userService.createOrUpdateUser(authentication);

        // Adiciona o usuário ao modelo
        model.addAttribute("user", user);

        return "dashboard";
    }
}
