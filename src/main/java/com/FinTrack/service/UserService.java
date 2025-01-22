package com.FinTrack.service;

import com.FinTrack.model.user.Users;
import com.FinTrack.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public Users createOrUpdateUser(Authentication authentication) {
        if (authentication.getPrincipal() instanceof Users) {
            return (Users) authentication.getPrincipal();
        } else if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
            String email = (String) oauthUser.getAttributes().get("email");
            String name = (String) oauthUser.getAttributes().get("name");

            return usersRepository.findByEmail(email)
                    .orElseGet(() -> {
                        Users newUser = new Users();
                        newUser.setEmail(email);
                        newUser.setName(name);
                        return usersRepository.save(newUser);
                    });
        }
        throw new IllegalStateException("Usuário não autenticado corretamente.");
    }
}
