package com.develhope.spring.features.user.model;

import com.develhope.spring.features.user.DTOs.UserRequest;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.entity.UserEntity;
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
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
    private String creditCard;
    private String address;

    public UserModel(String name, String surname, String phoneNumber, String email, String password, Role role, String creditCard, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
        this.address = address;
    }

    public UserModel(Long id, String name, String surname, String phoneNumber, String email, String password, Role role, String creditCard, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
        this.address = address;
    }

    public static UserResponse modelToDto(UserModel userModel) {
        return new UserResponse(userModel.getId(), userModel.getName(), userModel.getSurname(), userModel.getPhoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }

    public static UserModel dtoToModel(UserRequest request) {
        return new UserModel(request.getName(), request.getSurname(), request.getPhoneNumber(), request.getEmail(), request.getPassword(), request.getRole(), request.getCreditCard(), request.getAddress());
    }

    public static UserEntity modelToEntity(UserModel userModel) {
        return new UserEntity(userModel.getId(), userModel.getName(), userModel.getSurname(), userModel.getPhoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole(), userModel.getCreditCard(), userModel.getAddress());
    }

    public static UserModel entityToModel(UserEntity userEntity) {
        return new UserModel(userEntity.getUserId(), userEntity.getName(), userEntity.getSurname(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole(), userEntity.getPhoneNumber(), userEntity.getCreditCard(), userEntity.getAddress());
    }
}
