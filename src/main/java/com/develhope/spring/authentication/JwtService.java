package com.develhope.spring.authentication;

import com.develhope.spring.User.entity.User;
import com.develhope.spring.User.entity.Users;
import com.develhope.spring.authentication.entities.RefreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    RefreshToken generateRefreshToken(User user);

    boolean isRefreshTokenExpired(RefreshToken token);

    boolean isTokenValid(String token, UserDetails userDetails);

    @Service
    class JtwServiceImpl implements JtwService {
        @Value("${token.signing.key}")
        private String jwtSigningKey;

    }
}

