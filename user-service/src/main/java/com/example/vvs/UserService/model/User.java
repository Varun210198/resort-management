package com.example.vvs.UserService.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(unique = true)
    String email;
    Integer age;
    //Ordinal Stores as an integer in database
    @Enumerated(value = EnumType.STRING)
    Gender gender;
    String hashedPassword;
    @Column(unique = true)
    String phoneNumber;
    @CreatedDate
    LocalDateTime createdAt;
}
