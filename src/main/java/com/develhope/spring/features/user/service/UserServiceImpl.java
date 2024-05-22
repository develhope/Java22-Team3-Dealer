package com.develhope.spring.features.user.service;

import com.develhope.spring.features.user.DTOs.UserRequest;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;

    public UserResponse createUsers(UserRequest request) {
        UserModel model = UserModel.dtoToModel(request);
        UserEntity entity = UserModel.modelToEntity(model);
        UserEntity savedEntity = usersRepository.saveAndFlush(entity);
        UserModel savedModel = UserModel.entityToModel(savedEntity);
        UserResponse savedUser = UserModel.modelToDto(savedModel);
        return savedUser;
    }

    public boolean deleteUserById(Long userId) {
        UserEntity userEntity = usersRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            throw new IllegalArgumentException("No users found for the id: " + userId);
        } else {
            usersRepository.deleteById(userId);
            return true;
        }
    }

    public UserResponse findById(Long userId) {
        UserEntity userEntity = usersRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            throw new IllegalArgumentException("No users found for the id: " + userId);
        }
        UserModel userModel = UserModel.entityToModel(userEntity);
        UserResponse userFound = UserModel.modelToDto(userModel);
        return userFound;
    }

    public UserResponse updateUserById(long userId, UserRequest request) {
        Optional<UserEntity> result = usersRepository.findById(userId);

        if (result.isPresent()) {
            try {
                result.get().setName(request.getName()== null ? result.get().getName() : request.getName());
                result.get().setSurname(request.getSurname() == null ? result.get().getSurname() : request.getSurname());
                result.get().setEmail(request.getEmail() == null ? result.get().getEmail() : request.getEmail());
                result.get().setPassword(request.getPassword() == null ? result.get().getPassword() : request.getPassword());
                result.get().setTelephoneNumber(request.getTelephoneNumber() == null ? result.get().getTelephoneNumber() : request.getTelephoneNumber());
                result.get().setRole(request.getRole() == null ? result.get().getRole() : request.getRole());
                UserEntity savedUserEntity = usersRepository.saveAndFlush(result.get());
                UserModel savedUserModel = UserModel.entityToModel(savedUserEntity);
                return UserModel.modelToDto(savedUserModel);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    //TODO: o è meglio così?
    public UserResponse updateUser(UserDetails user, UserRequest request) {
        UserEntity toUpdate = usersRepository.findByEmail(String.valueOf(userDetailsService().loadUserByUsername(user.getUsername()))).orElse(null);
        if (toUpdate == null) {
            throw new IllegalArgumentException("No users found with this username: " + user.getUsername());
        }
        UserModel model = UserModel.dtoToModel(request);
        UserEntity entity = UserModel.modelToEntity(model);
        UserEntity savedEntity = usersRepository.saveAndFlush(entity);
        UserModel savedModel = UserModel.entityToModel(savedEntity);
        UserResponse updatedUser = UserModel.modelToDto(savedModel);
        return updatedUser;
    }

    public List<UserEntity> getAll() throws Exception {
        List<UserEntity> userEntities = usersRepository.findAll();
        if (userEntities.isEmpty()) {
            throw new Exception("Ops, looks like there is nothing here...");
        }
        return userEntities;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
