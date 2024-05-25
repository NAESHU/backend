package com.naeshu.naeshubackend.user.controller;

import com.naeshu.naeshubackend.auth.Auth;
import com.naeshu.naeshubackend.auth.jwt.JwtService;
import com.naeshu.naeshubackend.user.dto.request.UserLoginRequest;
import com.naeshu.naeshubackend.user.dto.request.UserSignUpRequest;
import com.naeshu.naeshubackend.user.dto.response.UserInfoResponse;
import com.naeshu.naeshubackend.user.dto.response.UserLoginResponse;
import com.naeshu.naeshubackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserSignUpRequest signUpRequest){
        userService.SignUp(signUpRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        Long userId = userService.Login(userLoginRequest);
        String accessToken = jwtService.createToken(userId, "USER");
        return ResponseEntity.ok(new UserLoginResponse(accessToken));
    }

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo(@Auth Long userId,
                                                        @Auth String ROLE){
        String userName = userService.findName(userId);
        UserInfoResponse userInfoResponse = new UserInfoResponse(userName, ROLE);
        return ResponseEntity.ok(userInfoResponse);
    }
}
