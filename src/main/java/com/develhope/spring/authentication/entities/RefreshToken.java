package com.develhope.spring.authentication.entities;

import com.develhope.spring.features.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private Instant expiringDate;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User userInfo;
}
