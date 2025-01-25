package com.finTrack.controller;

import com.finTrack.model.user.Users;
import com.finTrack.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    // Obter todos os usuários
    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Registrar novo usuário
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody Users user) {
//        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
//            return ResponseEntity.badRequest().body("O e-mail já está em uso.");
//        }
//        Users savedUser = usersRepository.save(user);
//        return ResponseEntity.ok(savedUser);
//    }
//
//    // Login do usuário
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody Users loginRequest) {
//        Optional<Users> user = usersRepository.findByEmail(loginRequest.getEmail());
//        if (user.isPresent() && loginRequest.getName().equals(user.get().getName())) {
//            return ResponseEntity.ok("Login realizado com sucesso.");
//        } else {
//            return ResponseEntity.status(401).body("Credenciais inválidas.");
//        }
//    }
//
//    // Logout do usuário (simulado, sem autenticação por sessão/jwt implementada)
//    @PostMapping("/logout")
//    public ResponseEntity<?> logoutUser() {
//        return ResponseEntity.ok("Logout realizado com sucesso.");
//    }
//
//    // Obter usuário por ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
//        return usersRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Atualizar usuário
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
//        Optional<Users> userOptional = usersRepository.findById(id);
//
//        if (!userOptional.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Users user = userOptional.get();
//        user.setName(userDetails.getName());
//        user.setEmail(userDetails.getEmail());
//        // Atualize outros campos conforme necessário
//        usersRepository.save(user);
//
//        return ResponseEntity.ok(user);
//    }
//
//    // Excluir usuário
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//        if (!usersRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        usersRepository.deleteById(id);
//        return ResponseEntity.ok("Usuário excluído com sucesso.");
//    }
}