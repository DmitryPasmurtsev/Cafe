package com.db.kursach.dto;

import com.db.kursach.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String login;
    private Role role;
    private EmployeeDTO employee;
}
