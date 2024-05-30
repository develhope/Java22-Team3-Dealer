package com.develhope.spring.features.authentication;

import com.develhope.spring.features.authentication.entities.RefreshToken;
import com.develhope.spring.features.user.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    RefreshToken generateRefreshToken(UserEntity user);

    boolean isRefreshTokenExpired(RefreshToken token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
