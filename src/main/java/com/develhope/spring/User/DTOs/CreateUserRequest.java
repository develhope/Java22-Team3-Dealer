package com.develhope.spring.User.DTOs;

import com.develhope.spring.User.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private Integer telephoneNumber;
    private String email;
    private String password;
    private Role role;
}
