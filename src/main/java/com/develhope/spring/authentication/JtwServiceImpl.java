package com.develhope.spring.authentication;

import com.develhope.spring.User.entity.User;
import com.develhope.spring.authentication.entities.RefreshToken;
import com.develhope.spring.authentication.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JtwServiceImpl implements JwtService{
    @Value("${token.signing.key}")
    private String signingKey;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims ::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return "";
    }
//TODO: questo metodo ci peermette di estrarre tutte le info dello user da un token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
    }

    @Override
    public RefreshToken generateRefreshToken(User user) {
        return null;
    }

    @Override
    public boolean isRefreshTokenExpired(RefreshToken token) {
        return false;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }
}
