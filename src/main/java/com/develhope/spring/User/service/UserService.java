package com.develhope.spring.User.service;

import com.develhope.spring.User.DTOs.CreateUserRequest;
import com.develhope.spring.User.DTOs.UserModel;
import com.develhope.spring.User.DTOs.UsersDTO;
import com.develhope.spring.User.entity.UserEntity;
import com.develhope.spring.User.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    public UsersDTO createUsers(CreateUserRequest request) {
        UserModel model = UserModel.dtoToModel(request);
        UserEntity entity = UserModel.modelToEntity(model);
        UserEntity savedEntity = usersRepository.saveAndFlush(entity);
        UserModel savedModel = UserModel.entityToModel(savedEntity);
        UsersDTO savedUser = UserModel.modelToDto(savedModel);
        return savedUser;
    }

    public void deleteUsersByID(Long userId) {
        UserEntity user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("No users found for the id: " + userId);
        } else {
            usersRepository.deleteById(userId);
        }
    }

    public UsersDTO findById(Long userId) {
        UserEntity user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("No users found for the id: " + userId);
        }
        UserModel userModel = UserModel.entityToModel(user);
        UsersDTO userFound = UserModel.modelToDto(userModel);
        return userFound;
    }

    public UsersDTO updateUser(Long userId, CreateUserRequest request) {
        UserEntity toUpdate = usersRepository.findById(userId).orElse(null);
        if (toUpdate == null) {
            throw new IllegalArgumentException("No users found for the id: " + userId);
        }
        UserModel model = UserModel.dtoToModel(request);
        UserEntity entity = UserModel.modelToEntity(model);
        UserEntity savedEntity = usersRepository.saveAndFlush(entity);
        UserModel savedModel = UserModel.entityToModel(savedEntity);
        UsersDTO updatedUser = UserModel.modelToDto(savedModel);
        toUpdate.setFirstName(updatedUser.getFirstName());
        toUpdate.setLastName(updatedUser.getLastName());
        toUpdate.setEmail(updatedUser.getEmail());
        toUpdate.setPassword(updatedUser.getPassword());
        toUpdate.setTelephoneNumber(updatedUser.getTelephoneNumber());
        usersRepository.saveAndFlush(toUpdate);
        return updatedUser;
    }


    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return usersRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
