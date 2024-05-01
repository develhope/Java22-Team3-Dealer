package com.develhope.spring.User.DTOs;

import com.develhope.spring.User.entity.Role;
import com.develhope.spring.User.entity.UserEntity;
import lombok.Data;

import java.time.OffsetDateTime;

@Data

public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer telephoneNumber;
    private String email;
    private String password;
    private Role role;

    public UserModel(Long id, String firstName, String lastName, Integer telephoneNumber, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserModel(String firstName, String lastName, Integer telephoneNumber, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UsersDTO modelToDto(UserModel userModel){
        return new UsersDTO(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getTelephoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }
    public static UserModel dtoToModel(CreateUserRequest request){
        return new UserModel(request.getFirstName(), request.getLastName(), request.getTelephoneNumber(), request.getEmail(), request.getPassword(), request.getRole());
    }
    public static UserEntity modelToEntity(UserModel userModel){
        return new UserEntity(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getTelephoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }
    public static UserModel entityToModel(UserEntity userEntity){
        return new UserModel(userEntity.getUserId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getTelephoneNumber(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());
    }
}
