package com.develhope.spring.Entities.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.DataAmount;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String address;
    private String creditCard;
    private String taxId;
    private UserTypeEnum role;

}
