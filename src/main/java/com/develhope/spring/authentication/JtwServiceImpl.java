package com.develhope.spring.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JtwServiceImpl implements JtwService{
    @Value("${token.signing.key}")
    private String jwtSigningKey;

}
