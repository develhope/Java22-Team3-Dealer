package com.develhope.spring.authentication;

import com.develhope.spring.authentication.entities.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {
}
