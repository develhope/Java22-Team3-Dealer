package com.develhope.spring.features.user.controller;

import com.develhope.spring.features.errors.GenericErrors;
import com.develhope.spring.features.user.DTOs.UserRequest;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.vavr.control.Either;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUsers(@AuthenticationPrincipal UserEntity userEntity, @RequestBody UserRequest request) {
        Either<GenericErrors, UserResponse> result = userServiceImpl.createUser(request);
        if (result.isLeft()) {
            return new ResponseEntity<>(result.getLeft().getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long userId) {
        Either<GenericErrors, Boolean> result = userServiceImpl.deleteUserById(userId);

        if (result.isLeft()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/findUser/{userId}")
    public ResponseEntity<?> getById(@PathVariable Long userId) {
        Either<GenericErrors, UserResponse> result = userServiceImpl.findById(userId);

        if (result.isLeft()) {
            return new ResponseEntity<>(result.getLeft().getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserRequest request) {
        Either<GenericErrors, UserResponse> result = userServiceImpl.updateUserById(userId, request);

        if (result.isLeft()) {
            return new ResponseEntity<>(result.getLeft().getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
    }


    @GetMapping("/GetAll")
    public ResponseEntity<?> getAllUsers() {
        Either<GenericErrors, List<UserEntity>> result = userServiceImpl.getAll();

        if (result.isLeft()) {
            return new ResponseEntity<>(result.getLeft().getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }

    }
}
