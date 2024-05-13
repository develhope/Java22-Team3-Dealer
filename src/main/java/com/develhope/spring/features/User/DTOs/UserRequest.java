package com.develhope.spring.features.User.DTOs;

import com.develhope.spring.features.User.entity.Role;
import lombok.*;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
        private String firstName;
        private String lastName;
        private String telephoneNumber;
        private String email;
        private String password;
        private Role role;
}
