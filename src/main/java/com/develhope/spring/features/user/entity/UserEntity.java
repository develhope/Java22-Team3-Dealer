package com.develhope.spring.features.user.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, name = "Name")
    private String name;
    @Column(nullable = false, name = "Surname")
    private String surname;
    @Column(nullable = false, name = "Telephone number")
    private String telephoneNumber;
    @Column(unique = true, name = "Email")
    private String email;
    @Column(nullable = false)
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + name + '\'' +
                ", lastName='" + surname + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
