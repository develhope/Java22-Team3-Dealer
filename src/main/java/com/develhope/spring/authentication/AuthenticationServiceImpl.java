package com.develhope.spring.authentication;

import com.develhope.spring.features.user.entity.Role;
import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.features.user.repository.UsersRepository;
import com.develhope.spring.authentication.entities.RefreshToken;
import com.develhope.spring.authentication.DTOs.requests.RefreshTokenRequest;
import com.develhope.spring.authentication.DTOs.requests.SignInRequest;
import com.develhope.spring.authentication.DTOs.requests.SignUpRequest;
import com.develhope.spring.authentication.DTOs.responses.JwtAuthenticationResponse;
import com.develhope.spring.authentication.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UsersRepository userRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        UserEntity userEntity = UserEntity.builder()
                .name(request.getFirstName())
                .surname(request.getLastName())
                .telephoneNumber(request.getTelephoneNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.convertStringToRole(request.getRole())).build();

        userRepository.save(userEntity);
        String jwt = jwtService.generateToken((UserDetails) userEntity);
        RefreshToken refreshToken = jwtService.generateRefreshToken(userEntity);
        return JwtAuthenticationResponse.builder().authToken(jwt).refreshToken(refreshToken.getToken()).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        var jwt = jwtService.generateToken((UserDetails) user);
        RefreshToken refreshToken = jwtService.generateRefreshToken(user);
        return JwtAuthenticationResponse.builder().authToken(jwt).refreshToken(refreshToken.getToken()).build();
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken());

        if (refreshToken.isPresent() && !jwtService.isRefreshTokenExpired(refreshToken.get())) {
            var user = userRepository.findByEmail(refreshToken.get().getUserEntityInfo().getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

            var jwt = jwtService.generateToken((UserDetails) user);

            return JwtAuthenticationResponse.builder().authToken(jwt).refreshToken(refreshToken.get().getToken()).build();
        } else {
            return JwtAuthenticationResponse.builder().build();
        }
    }
}