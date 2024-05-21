package com.develhope.spring.authentication.repository;

import com.develhope.spring.features.user.entity.UserEntity;
import com.develhope.spring.authentication.entities.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findByUserInfo(UserEntity userEntity);
}
