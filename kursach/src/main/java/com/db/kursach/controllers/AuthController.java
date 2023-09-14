package com.db.kursach.controllers;

import com.db.kursach.dto.AuthRequest;
import com.db.kursach.dto.AuthResponse;
import com.db.kursach.dto.RegisterRequest;
import com.db.kursach.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(
            @RequestBody RegisterRequest request
    ) {
        if (!authService.registration(request)) {
            return new ResponseEntity<>(
                    "Ошибка регистрации пользователя с email: " + request.getEmail(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                "Пользователь с логином " + request.getLogin() + " успешно зарегистрирован",
                HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> authentication(
            @RequestBody AuthRequest request
    ) {
        try {
        AuthResponse authResponse = authService.authentication(request);
        return ResponseEntity.ok(authResponse);
    } catch (BadCredentialsException ex) {
        return new ResponseEntity<>(
                "Неверный логин или пароль",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
