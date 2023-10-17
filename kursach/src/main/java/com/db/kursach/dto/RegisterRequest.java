package com.db.kursach.dto;

import lombok.*;

@Getter
@Setter
@Builder

public class RegisterRequest {
    private String login;
    private String email;
    private String password;
}
