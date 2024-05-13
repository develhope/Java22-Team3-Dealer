package com.develhope.spring.authentication;

import com.develhope.spring.features.User.entity.User;
import com.develhope.spring.authentication.entities.RefreshToken;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    RefreshToken generateRefreshToken(User user);

    boolean isRefreshTokenExpired(RefreshToken token);

    boolean isTokenValid(String token, UserDetails userDetails);
}

