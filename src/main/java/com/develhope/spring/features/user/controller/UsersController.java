package com.develhope.spring.features.user.controller;

import com.develhope.spring.features.user.DTOs.UserRequest;
import com.develhope.spring.features.user.DTOs.UserResponse;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @Operation(summary = "Create userEntity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new userEntity has been added to your table"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PostMapping("/createUser")
    public ResponseEntity<?> createUsers(@AuthenticationPrincipal UserEntity userEntity, @RequestBody UserRequest request){
        UserResponse saveUsers = userServiceImpl.createUsers(request);
        return new ResponseEntity<>(saveUsers, HttpStatus.CREATED);
    }
    @Operation(summary = "Delete Users by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id){
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update Users by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserRequest request){
        UserResponse updatedUser = userServiceImpl.updateUserById(userId, request);
         return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Find Users by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Research executed successfully!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @GetMapping("/findUser/{userId}")
    public ResponseEntity<?> getById(@PathVariable Long userId){
        return new ResponseEntity<>(userServiceImpl.findById(userId),HttpStatus.OK);
    }
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>(userServiceImpl.getAll(),HttpStatus.OK);
    }

}
