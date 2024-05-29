package com.develhope.spring.features.user.DTOs;

import com.develhope.spring.features.user.entity.Role;
import lombok.*;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
        private String name;
        private String surname;
        private String phoneNumber;
        private String email;
        private String password;
        private Role role;
        private String creditCard;
        private String address;
}
