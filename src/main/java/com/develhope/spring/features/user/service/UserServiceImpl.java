package com.develhope.spring.features.user.service;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.errors.UserError;
import com.develhope.spring.features.user.DTOs.UserRequest;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.repository.UsersRepository;
import io.vavr.control.Either;
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

    public Either<GenericErrors, UserResponse> createUser(UserRequest createUserRequest) {
        try {
            UserModel model = UserModel.dtoToModel(createUserRequest);
            UserEntity entity = UserModel.modelToEntity(model);
            UserEntity savedEntity = usersRepository.saveAndFlush(entity);
            UserModel savedModel = UserModel.entityToModel(savedEntity);
            UserResponse savedUser = UserModel.modelToDto(savedModel);
            return Either.right(savedUser);
        } catch (Exception e) {
            return Either.left(new UserError.ImpossibleToCreateUser(e));
        }
    }

    public Either<GenericErrors, Boolean> deleteUserById(Long userId) {
        try {
            UserEntity userEntity = usersRepository.findById(userId).orElse(null);
            if (userEntity == null) {
                return Either.left(new GenericErrors(435, "No users found for the id: " + userId));
            } else {
                usersRepository.deleteById(userId);
                return Either.right(true);
            }
        } catch (Exception e) {
            return Either.left(new GenericErrors(435, "Failed to delete user: " + e.getMessage()));
        }
    }

    public Either<GenericErrors, UserResponse> findById(Long userId) {
        try {
            UserEntity userEntity = usersRepository.findById(userId).orElse(null);
            if (userEntity == null) {
                return Either.left(new GenericErrors(433, "No users found for the id: " + userId));
            }
            UserModel userModel = UserModel.entityToModel(userEntity);
            UserResponse userFound = UserModel.modelToDto(userModel);
            return Either.right(userFound);
        } catch (Exception e) {
            return Either.left(new GenericErrors(437, "Failed to find user: " + e.getMessage()));
        }
    }

    public Either<GenericErrors, UserResponse> updateUserById(long userId, UserRequest request) {
        Optional<UserEntity> result = usersRepository.findById(userId);

        if (result.isPresent()) {
            try {
                UserEntity userEntity = result.get();
                userEntity.setName(request.getName() == null ? userEntity.getName() : request.getName());
                userEntity.setSurname(request.getSurname() == null ? userEntity.getSurname() : request.getSurname());
                userEntity.setEmail(request.getEmail() == null ? userEntity.getEmail() : request.getEmail());
                userEntity.setPassword(request.getPassword() == null ? userEntity.getPassword() : request.getPassword());
                userEntity.setTelephoneNumber(request.getTelephoneNumber() == null ? userEntity.getTelephoneNumber() : request.getTelephoneNumber());
                userEntity.setRole(request.getRole() == null ? userEntity.getRole() : request.getRole());

                UserEntity savedUserEntity = usersRepository.saveAndFlush(userEntity);
                UserModel savedUserModel = UserModel.entityToModel(savedUserEntity);
                UserResponse userResponse = UserModel.modelToDto(savedUserModel);

                return Either.right(userResponse);
            } catch (Exception e) {
                return Either.left(new GenericErrors(438, "Failed to update user: " + e.getMessage()));
            }
        } else {
            return Either.left(new GenericErrors(438, "No users found for the id: " + userId));
        }
    }

    public Either<GenericErrors, List<UserEntity>> getAll() {
        try {
            List<UserEntity> userEntities = usersRepository.findAll();
            if (userEntities.isEmpty()) {
                return Either.left(new GenericErrors(445, "Ops, looks like there is nothing here..."));
            }
            return Either.right(userEntities);
        } catch (Exception e) {
            return Either.left(new GenericErrors(445, "Failed to retrieve users: " + e.getMessage()));
        }
    }


    @Override
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

}
