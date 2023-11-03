package com.db.kursach.dto.auth;

import com.db.kursach.dto.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    String token;
    UserDTO user; //если ты закомментил это для авторизации то надо поменять на дто
}
