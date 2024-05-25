package com.naeshu.naeshubackend.user;

import com.naeshu.naeshubackend.common.UnAuthorizedException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String userPassword;
    private String userName;
    private Long userPoint;
    private LocalDate userBirth;

    public User(String userId, String userPassword, String userName, Long userPoint, LocalDate userBirth) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPoint = userPoint;
        this.userBirth = userBirth;
    }
    public void login(String companyPassword) {
        if (this.userPassword.equals(companyPassword)) {
            return;
        }
        throw new UnAuthorizedException("비밀번호가 일치하지 않습니다.");
    }
}
