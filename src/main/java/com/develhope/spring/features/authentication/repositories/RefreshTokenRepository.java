package com.develhope.spring.features.authentication.repositories;

import com.develhope.spring.features.authentication.entities.RefreshToken;
import com.develhope.spring.features.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findByUserInfo(UserEntity user);
}
