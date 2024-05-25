package com.naeshu.naeshubackend.user.dto.request;

import java.time.LocalDate;

public record UserSignUpRequest(
        String userId,
        String userPassword,
        String userName,
        LocalDate userBirth) {

}
