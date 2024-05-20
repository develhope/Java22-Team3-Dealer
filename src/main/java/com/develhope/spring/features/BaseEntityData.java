package com.develhope.spring.features;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntityData {

    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime updatedAt = LocalDateTime.now();
    private final LocalDateTime deletedAt = LocalDateTime.now();

}
