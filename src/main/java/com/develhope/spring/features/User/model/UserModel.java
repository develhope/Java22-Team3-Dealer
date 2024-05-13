package com.develhope.spring.features.User.model;

import com.develhope.spring.features.User.DTOs.UserRequest;
import com.develhope.spring.features.User.DTOs.UserResponse;
import com.develhope.spring.features.User.entity.Role;
import com.develhope.spring.features.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private String email;
    private String password;
    private Role role;

    public UserModel(String firstName, String lastName, String telephoneNumber, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserResponse modelToDto(UserModel userModel){
        return new UserResponse(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getTelephoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }
    public static UserModel dtoToModel(UserRequest request){
        return new UserModel(request.getFirstName(), request.getLastName(), request.getTelephoneNumber(), request.getEmail(), request.getPassword(), request.getRole());
    }
    public static User modelToEntity(UserModel userModel){
        return new User(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getTelephoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }
    public static UserModel entityToModel(User user){
        return new UserModel(user.getUserId(), user.getFirstName(), user.getLastName(), user.getTelephoneNumber(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
