package com.FinTrack.controller;

import com.FinTrack.model.user.Users;
import com.FinTrack.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller; // Certifique-se de usar Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // Modificado para Controller
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";  // Renderiza o template login.html
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/";  // Redireciona para a página de login se o usuário não estiver autenticado
        }

        Users user = (Users) authentication.getPrincipal();
        model.addAttribute("user", user);

        return "dashboard";  // Renderiza o template dashboard.html
    }
}
