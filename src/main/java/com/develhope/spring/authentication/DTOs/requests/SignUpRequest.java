package com.develhope.spring.authentication.DTOs.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
        private String firstName;
        private String lastName;
        private String telephoneNumber;
        private String email;
        private String password;
        private String role;
}
