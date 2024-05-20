package com.develhope.spring.authentication;

import com.develhope.spring.authentication.DTOs.requests.RefreshTokenRequest;
import com.develhope.spring.authentication.DTOs.requests.SignInRequest;
import com.develhope.spring.authentication.DTOs.requests.SignUpRequest;
import com.develhope.spring.authentication.DTOs.responses.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest request);
}