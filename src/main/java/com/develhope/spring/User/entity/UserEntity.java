package com.develhope.spring.User.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table
@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, name = "Name")
    private String firstName;
    @Column(nullable = false, name = "Surname")
    private String lastName;
    @Column(nullable = false, name = "Telephone number")
    private Integer telephoneNumber;
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
