package com.develhope.spring.features.user.repository;

import com.develhope.spring.features.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT * FROM users WHERE role = 'SALESMAN'", nativeQuery = true)
    List<User> findAllSalesman();

    @Query(value = "SELECT * FROM users WHERE role = 'CUSTOMER'", nativeQuery = true)
    List<User> findAllCustomer();

}
