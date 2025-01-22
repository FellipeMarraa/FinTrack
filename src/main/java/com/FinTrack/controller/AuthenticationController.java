package com.FinTrack.controller;

import com.FinTrack.model.enums.UserRole;
import com.FinTrack.model.user.AuthenticationDTO;
import com.FinTrack.model.user.LoginResponseDTO;
import com.FinTrack.model.user.RegisterDTO;
import com.FinTrack.model.user.Users;
import com.FinTrack.repository.UsersRepository;
import com.FinTrack.security.TokenService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String client_google;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        UsernamePasswordAuthenticationToken usernamePassoword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassoword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> payload) {
        String googleToken = payload.get("token");

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(client_google))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(googleToken);
            if (idToken != null) {
                GoogleIdToken.Payload tokenPayload = idToken.getPayload();

                String email = tokenPayload.getEmail();
                String name = (String) tokenPayload.get("name");
                String picture = (String) tokenPayload.get("picture");

                Users user = usersRepository.findByEmail(email).orElseGet(() -> {
                    Users newUser = new Users();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    newUser.setPicture(picture);
                    newUser.setAuthType("GOOGLE");
                    return usersRepository.save(newUser);
                });

                String token = tokenService.generateToken(user);
                return ResponseEntity.ok(new LoginResponseDTO(token));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid Google token");
        }

        return ResponseEntity.status(401).body("Google authentication failed");
    }




    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO register) {
        if (usersRepository.findByEmail(register.email()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        String encryptedPassword = register.authType().equals("EMAIL")
                ? new BCryptPasswordEncoder().encode(register.password())
                : null;

        Users newUser = new Users(
                register.email(),
                encryptedPassword
        );

        newUser.setAuthType("EMAIL");

        usersRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
