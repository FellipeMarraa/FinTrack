package com.FinTrack.service;

import com.FinTrack.model.User;
import com.FinTrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Map<String, Object> getLoggedUserAttributes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof OidcUser) {
            return ((OidcUser) principal).getAttributes(); // OpenID Connect (ex.: Google)
        } else if (principal instanceof OAuth2User) {
            return ((OAuth2User) principal).getAttributes(); // OAuth2 (ex.: GitHub)
        }

        throw new IllegalStateException("Usuário não está autenticado ou não é um OAuth2/OIDC usuário.");
    }

    public User createOrUpdateUser(Authentication authentication) {
        User user = new User();

        // Verifica se é um usuário OAuth2 (Google, etc.)
        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

            user.setName(oauth2User.getAttribute("name"));
            user.setEmail(oauth2User.getAttribute("email"));
            user.setPicture(oauth2User.getAttribute("picture"));
            user.setAuthType("google");

        } else {
            // Usuário local (autenticação por login e senha)
            user.setName(authentication.getName());
            user.setEmail(authentication.getName()); // Substitua pelo e-mail se disponível
            user.setAuthType("local");
        }

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            user.setId(existingUser.get().getId()); // Preserva o ID
        }

        return userRepository.save(user);
    }
}
