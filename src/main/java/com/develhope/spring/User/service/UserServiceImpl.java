package com.develhope.spring.User.service;

import com.develhope.spring.User.DTOs.CreateUserRequest;
import com.develhope.spring.User.model.UserModel;
import com.develhope.spring.User.DTOs.UserResponse;
import com.develhope.spring.User.entity.User;
import com.develhope.spring.User.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;
    public UserResponse createUsers(CreateUserRequest request) {
        UserModel model = UserModel.dtoToModel(request);
        User entity = UserModel.modelToEntity(model);
        User savedEntity = usersRepository.saveAndFlush(entity);
        UserModel savedModel = UserModel.entityToModel(savedEntity);
        UserResponse savedUser = UserModel.modelToDto(savedModel);
        return savedUser;
    }

    public boolean deleteUserById(Long userId) {
        User user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("No users found for the id: " + userId);
        } else {
            usersRepository.deleteById(userId);
            return true;
        }
    }
    //TODO: questo metodo per la ricerca di user è corretto?
    public boolean deleteUser(User userEmail) {
        User user = usersRepository.findByEmail(String.valueOf(userDetailsService().loadUserByUsername(userEmail.getEmail()))).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("No users found with this email address: " + user.getEmail());
        } else {
            usersRepository.delete(user);
            return true;
        }
    }

    public UserResponse findById(Long userId) {
        User user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("No users found for the id: " + userId);
        }
        UserModel userModel = UserModel.entityToModel(user);
        UserResponse userFound = UserModel.modelToDto(userModel);
        return userFound;
    }

    public UserResponse updateUserById(Long userId, CreateUserRequest request) {
        User toUpdate = usersRepository.findById(userId).orElse(null);
        if (toUpdate == null) {
            throw new IllegalArgumentException("No users found for the id: " + userId);
        }
        UserModel model = UserModel.dtoToModel(request);
        User entity = UserModel.modelToEntity(model);
        User savedEntity = usersRepository.saveAndFlush(entity);
        UserModel savedModel = UserModel.entityToModel(savedEntity);
        UserResponse updatedUser = UserModel.modelToDto(savedModel);
        return updatedUser;
    }
    //TODO: o è megli così?
    public UserResponse updateUser(UserDetails user, CreateUserRequest request) {
        User toUpdate = usersRepository.findByEmail(String.valueOf(userDetailsService().loadUserByUsername(user.getUsername()))).orElse(null);
        if (toUpdate == null) {
            throw new IllegalArgumentException("No users found with this username: " + user.getUsername());
        }
        UserModel model = UserModel.dtoToModel(request);
        User entity = UserModel.modelToEntity(model);
        User savedEntity = usersRepository.saveAndFlush(entity);
        UserModel savedModel = UserModel.entityToModel(savedEntity);
        UserResponse updatedUser = UserModel.modelToDto(savedModel);
        return updatedUser;
    }

    public List<User> getAll() throws Exception {
        List<User> users = usersRepository.findAll();
        if (users.isEmpty()) {
            throw new Exception("Ops, looks like there is nothing here...");
        }
        return users;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return (UserDetails) usersRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
