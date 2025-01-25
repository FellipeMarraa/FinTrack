package com.FinTrack.controller;

import com.FinTrack.model.user.AuthenticationDTO;
import com.FinTrack.model.user.LoginResponseDTO;
import com.FinTrack.model.user.RegisterDTO;
import com.FinTrack.model.user.Users;
import com.FinTrack.repository.UsersRepository;
import com.FinTrack.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (Users) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token, user));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO register){

        if (usersRepository.findByEmail(register.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());

        Users newUser = new Users(register.email(), encryptedPassword);

        if (register.role() != null){
            newUser.setRole(register.role());
        }

        usersRepository.save(newUser);

        return ResponseEntity.ok().build();

    }

}