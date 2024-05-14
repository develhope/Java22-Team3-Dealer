package com.develhope.spring.features.user.model;

import com.develhope.spring.features.user.DTOs.UserRequest;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class UserModel {

    private Long id;
    private String name;
    private String surname;
    private String telephoneNumber;
    private String email;
    private String password;
    private Role role;

    public UserModel(String name, String surname, String telephoneNumber, String email, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserResponse modelToDto(UserModel userModel){
        return new UserResponse(userModel.getId(), userModel.getName(), userModel.getSurname(), userModel.getTelephoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }
    public static UserModel dtoToModel(UserRequest request){
        return new UserModel(request.getName(), request.getSurname(), request.getTelephoneNumber(), request.getEmail(), request.getPassword(), request.getRole());
    }
    public static User modelToEntity(UserModel userModel){
        return new User(userModel.getId(), userModel.getName(), userModel.getSurname(), userModel.getTelephoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }
    public static UserModel entityToModel(User user){
        return new UserModel(user.getUserId(), user.getName(), user.getSurname(), user.getTelephoneNumber(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
