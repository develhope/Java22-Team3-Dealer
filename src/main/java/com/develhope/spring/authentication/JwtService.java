package com.develhope.spring.authentication;

import com.develhope.spring.User.entity.Users;
import com.develhope.spring.authentication.entities.RefreshToken;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    RefreshToken generateRefreshToken(Users user);

    boolean isRefreshTokenExpired(RefreshToken token);

    boolean isTokenValid(String token, UserDetails userDetails);
}

