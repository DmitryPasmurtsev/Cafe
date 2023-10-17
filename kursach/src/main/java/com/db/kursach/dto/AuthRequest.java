package com.db.kursach.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class AuthRequest {
    String login;
    String password;
}
