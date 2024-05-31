package com.develhope.spring.features.user.DTOs;

import com.develhope.spring.features.user.entity.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
    private Long creditCard;
    private String address;

    public UserResponse(Long id, String name, String surname, String phoneNumber, String email, Role role, Long creditCard, String address) {

    }
}