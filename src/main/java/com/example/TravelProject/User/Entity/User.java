package com.example.TravelProject.User.Entity;

import com.example.TravelProject.Enum.UserStatus;
import com.example.TravelProject.User.Dto.UserPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Setter
    @Column
    private String name;

    @Setter
    @Column
    private String email;

    @Setter
    @Column
    private String password;

    @CreatedDate
    @Column
    private LocalDateTime cratedAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;

    @Enumerated(value = EnumType.STRING)
    @Setter
    @Column
    private UserStatus userStatus;

    @Setter
    @Column
    private Integer likeCount;


    public User(UserPostDto userPostDto) {
        this.name = userPostDto.getName();
        this.email = userPostDto.getEmail();
        this.password = userPostDto.getPassword();
    }
}
