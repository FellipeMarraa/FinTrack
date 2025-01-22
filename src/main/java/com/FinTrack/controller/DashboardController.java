package com.FinTrack.controller;

import com.FinTrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

//    @GetMapping("/dashboard")
//    public String dashboard(Model model, Authentication authentication) {
//        // Recupera ou cria um objeto User baseado no usuário autenticado
//        Users user = userService.createOrUpdateUser(authentication);
//
//        // Adiciona o usuário ao modelo
//        model.addAttribute("user", user);
//
//        return "dashboard";
//    }
}
