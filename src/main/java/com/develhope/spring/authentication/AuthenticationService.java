package com.develhope.spring.authentication;

import com.develhope.spring.authentication.DTOs.request.RefreshTokenRequest;
import com.develhope.spring.authentication.DTOs.request.SignInRequest;
import com.develhope.spring.authentication.DTOs.request.SignUpRequest;
import com.develhope.spring.authentication.DTOs.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest request);
}