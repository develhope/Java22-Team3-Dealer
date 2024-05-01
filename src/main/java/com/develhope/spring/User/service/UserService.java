package com.develhope.spring.User.service;

import com.develhope.spring.User.DTOs.CreateUserRequest;
import com.develhope.spring.User.DTOs.UserModel;
import com.develhope.spring.User.DTOs.UsersDTO;
import com.develhope.spring.User.entity.Users;
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
        Users entity = UserModel.modelToEntity(model);
        Users savedEntity = usersRepository.saveAndFlush(entity);
        UserModel savedModel = UserModel.entityToModel(savedEntity);
        UsersDTO savedUser = UserModel.modelToDto(savedModel);
        return savedUser;
    }

    public void deleteUsersByID(Long userId) {
        Users user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("Buyer not found by ID : " + userId);
        } else {
            usersRepository.deleteById(userId);
        }
    }

    public UsersDTO findById(Long userId) {
        Users user = usersRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("Buyer not found by ID : " + userId);
        }
        return convertToDTO(user);
    }

    public UsersDTO updateUser(Long userId, UsersDTO usersDTO) {
        Users usersUpdate = usersRepository.findById(userId).orElse(null);
        if (usersUpdate == null) {
            throw new IllegalArgumentException("Unable to update buyer with this ID : " + userId);
        }
        Users saveUsersDTO = convertToEntity(usersDTO);
        usersUpdate.setFirstName(saveUsersDTO.getFirstName());
        usersUpdate.setLastName(saveUsersDTO.getLastName());
        usersUpdate.setEmail(saveUsersDTO.getEmail());
        usersUpdate.setPassword(saveUsersDTO.getPassword());
        usersUpdate.setTelephoneNumber(saveUsersDTO.getTelephoneNumber());
        usersRepository.save(usersUpdate);
        return usersDTO;
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
