package com.develhope.spring.features.User.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService{
    UserDetailsService userDetailsService();
}
