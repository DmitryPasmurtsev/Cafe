package com.db.kursach.dto.auth;

//import com.db.kursach.models.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    String token;
    //private User user; если ты закомментил это для авторизации то надо поменять на дто
}
