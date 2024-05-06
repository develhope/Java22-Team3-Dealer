package com.develhope.spring.User.DTOs;

import com.develhope.spring.User.entity.Role;
import com.develhope.spring.User.entity.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
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
    }}

//    public static UsersDTO modelToDto(UserModel userModel){
//        return new UsersDTO(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getTelephoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
//    }
//
//    public static Users modelToEntity(UserModel userModel){
//        return new Users(userModel.getId(), userModel.getFirstName(), userModel.getLastName(), userModel.getTelephoneNumber(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
//    }
//    public static UserModel entityToModel(Users userEntity){
//        return new UserModel(userEntity.getUserId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getTelephoneNumber(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());
//    }
//}
