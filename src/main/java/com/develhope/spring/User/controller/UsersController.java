package com.develhope.spring.User.controller;

import com.develhope.spring.User.DTOs.CreateUserRequest;
import com.develhope.spring.User.DTOs.UsersDTO;
import com.develhope.spring.User.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;

    @Operation(summary = "Create UserEntity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PostMapping("/createUser")
    public ResponseEntity<UsersDTO> createUsers(@RequestBody CreateUserRequest request){
        UsersDTO saveUsers = userService.createUsers(request);
        return new ResponseEntity<>(saveUsers, HttpStatus.CREATED);
    }
    @Operation(summary = "Delete UserEntity by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long userId){
        userService.deleteUsersByID(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Update UserEntity by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PutMapping("/updateUser/{userId}")
    public UsersDTO updateUser(@PathVariable Long userId, @RequestBody UsersDTO usersDTO){
        return userService.updateUser(userId, usersDTO);
    }
    @Operation(summary = "Find UserEntity by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @GetMapping("/findUser/{userId}")
    public ResponseEntity<UsersDTO> findById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.findById(userId),HttpStatus.OK);
    }

}
