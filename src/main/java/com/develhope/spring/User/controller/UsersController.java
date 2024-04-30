package com.develhope.spring.User.controller;

import com.develhope.spring.User.dto.UsersDTO;
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

    @Operation(summary = "Create Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PostMapping("/createUser")
    public ResponseEntity<UsersDTO> createUsers(@RequestBody UsersDTO usersDTO){
        UsersDTO saveUsers = userService.createUsers(usersDTO);
        return new ResponseEntity<>(saveUsers, HttpStatus.CREATED);
    }
    @Operation(summary = "Delete Users by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long userId){
        userService.deleteUsersByID(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Update Users by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PutMapping("/updateUser/{userId}")
    public UsersDTO updateUser(@PathVariable Long userId, @RequestBody UsersDTO usersDTO){
        return userService.updateUser(userId, usersDTO);
    }
    @Operation(summary = "Find Users by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @GetMapping("/findUser/{userId}")
    public ResponseEntity<UsersDTO> findById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.findById(userId),HttpStatus.OK);
    }

}
