package com.develhope.spring.User.DTOs;

import com.develhope.spring.User.entity.Role;
import lombok.*;
@Data
@Getter
@Setter
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
