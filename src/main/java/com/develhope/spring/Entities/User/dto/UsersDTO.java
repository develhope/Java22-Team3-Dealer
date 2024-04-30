package com.develhope.spring.Entities.User.dto;

import com.develhope.spring.Entities.User.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private String firstName;
    private String lastName;
    private Integer telephoneNumber;
    private String email;
    private String password;
    private Role role;

    @Override
    public String toString() {
        return "UsersDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephoneNumber=" + telephoneNumber +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}