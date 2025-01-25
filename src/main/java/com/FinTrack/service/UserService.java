package com.FinTrack.service;

import com.FinTrack.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

//    public Map<String, Object> getLoggedUserAttributes() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//
//        if (principal instanceof OidcUser) {
//            return ((OidcUser) principal).getAttributes();
//        } else if (principal instanceof OAuth2User) {
//            return ((OAuth2User) principal).getAttributes();
//        }
//
//        throw new IllegalStateException("Usuário não está autenticado ou não é um OAuth2/OIDC usuário.");
//    }

//    public Users createOrUpdateUser(Authentication authentication) {
//        Users user = new Users();
//
//        if (authentication.getPrincipal() instanceof OAuth2User) {
//            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
//
//            user.setName(oauth2User.getAttribute("name"));
//            user.setEmail(oauth2User.getAttribute("email"));
//            user.setPicture(oauth2User.getAttribute("picture"));
//            user.setAuthType("google");
//
//        } else {
//            user.setName(authentication.getName());
//            user.setEmail(authentication.getName()); // Substitua pelo e-mail se disponível
//            user.setAuthType("local");
//        }
//
//        UserDetails existingUser = usersRepository.findByEmail(user.getEmail());
//        if (existingUser.isPresent()) {
//            user.setId(existingUser.get().getId()); // Preserva o ID
//        }
//
//        return usersRepository.save(user);
//    }
}