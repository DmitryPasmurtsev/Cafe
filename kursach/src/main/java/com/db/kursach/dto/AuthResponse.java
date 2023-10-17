package com.db.kursach.dto;

//import com.db.kursach.models.User;
import lombok.*;

@Getter
@Setter
@Builder
public class AuthResponse {
    private String token;
    //private User user; если ты закомментил это для авторизации то надо поменять на дто
}
