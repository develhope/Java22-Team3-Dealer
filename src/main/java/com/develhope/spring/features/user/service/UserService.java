package com.develhope.spring.features.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService{
    UserDetailsService userDetailsService();
}
