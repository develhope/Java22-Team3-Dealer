package com.develhope.spring.features.user.DTOs;

import com.develhope.spring.features.user.entity.Role;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private String email;
    private Role role;
}