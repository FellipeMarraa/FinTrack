package com.FinTrack.controller;

import com.FinTrack.model.user.Users;
import com.FinTrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> dashboard(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
        }

        Users user = (Users) authentication.getPrincipal();
        return ResponseEntity.ok(Map.of(
                "email", user.getEmail(),
                "name", user.getName(),
                "picture", user.getPicture()
        ));
    }

}
