package com.db.kursach.controllers;

import com.db.kursach.dto.auth.AuthRequest;
import com.db.kursach.dto.auth.AuthResponse;
import com.db.kursach.dto.auth.RegisterRequest;
import com.db.kursach.services.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {


    private final AuthServiceImpl authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegisterRequest request) {
        if (!authService.registration(request)) {
            return new ResponseEntity<>("Ошибка регистрации пользователя с email: " + request.getEmail(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                "Пользователь с логином " + request.getLogin() + " успешно зарегистрирован",
                HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> authentication(@RequestBody AuthRequest request) {
        try {
        AuthResponse authResponse = authService.authentication(request);
        return ResponseEntity.ok(authResponse);
        } catch (BadCredentialsException ex) {
        return new ResponseEntity<>("Неверный логин или пароль", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
