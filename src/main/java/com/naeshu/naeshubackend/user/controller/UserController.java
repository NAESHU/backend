package com.naeshu.naeshubackend.user.controller;

import com.naeshu.naeshubackend.auth.Auth;
import com.naeshu.naeshubackend.auth.AuthInfo;
import com.naeshu.naeshubackend.auth.jwt.JwtService;
import com.naeshu.naeshubackend.company.CompanyInfoResponse;
import com.naeshu.naeshubackend.company.CompanyService;
import com.naeshu.naeshubackend.user.dto.request.UserLoginRequest;
import com.naeshu.naeshubackend.user.dto.request.UserSignUpRequest;
import com.naeshu.naeshubackend.user.dto.response.UserInfoResponse;
import com.naeshu.naeshubackend.user.dto.response.UserLoginResponse;
import com.naeshu.naeshubackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/naeshu/user")
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final CompanyService companyService;

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

    @GetMapping("")
    public ResponseEntity<Object> getUserInfo(@Auth AuthInfo authInfo){
        String role = verifyRole(authInfo);
        if(role.equals("COMPANY")){
            String companyName = companyService.findNameById(authInfo.memberId());
            CompanyInfoResponse companyInfoResponse = new CompanyInfoResponse(companyName, role);
            return ResponseEntity.ok(companyInfoResponse);
        }
        String userName = userService.findNameById(authInfo.memberId());
        UserInfoResponse userInfoResponse = new UserInfoResponse(userName, role);
        return ResponseEntity.ok(userInfoResponse);
    }

    private String verifyRole(AuthInfo authInfo) {
        log.info("{}", authInfo.role());
        if (authInfo.role().equals("USER")) {
            return "USER";
        }
        return "COMPANY";
    }
}
