package com.finTrack.controller;

import com.finTrack.model.user.*;
import com.finTrack.repository.UsersRepository;
import com.finTrack.security.TokenService;
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

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setId(user.getId());
        userResponseDTO.setPicture(user.getPicture());

        return ResponseEntity.ok(new LoginResponseDTO(token, userResponseDTO));
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